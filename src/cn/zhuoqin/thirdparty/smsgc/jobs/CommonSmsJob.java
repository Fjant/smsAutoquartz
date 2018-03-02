package cn.zhuoqin.thirdparty.smsgc.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuoqin.platform.config.SystemConfig;
import cn.zhuoqin.platform.jdbc.DataSourceUtil;
import cn.zhuoqin.platform.job.model.JobEntity;
import cn.zhuoqin.platform.job.model.JobLog;
import cn.zhuoqin.platform.job.service.JobLogService;
import cn.zhuoqin.platform.job.support.BaseJob;
import cn.zhuoqin.platform.util.DateUtil;
import cn.zhuoqin.platform.util.StringUtil;
import cn.zhuoqin.thirdparty.smsgc.emailservice.SendMail;
import cn.zhuoqin.thirdparty.smsgc.jdbchelper.ListColumnMapBeanCreator;
import cn.zhuoqin.thirdparty.smsgc.jobs.model.CompDept;
import cn.zhuoqin.thirdparty.smsgc.sms.model.Sendrop;
import cn.zhuoqin.thirdparty.smsgc.sms.model.SmsInfo;
import cn.zhuoqin.thirdparty.smsgc.sms.service.SmsService;
import cn.zhuoqin.thirdparty.smsgc.smstmpl.model.SmsTmpl;
import cn.zhuoqin.thirdparty.smsgc.smstmpl.service.SmsTmplService;
import cn.zhuoqin.thirdparty.smsgc.util.SmsInspector;
import cn.zhuoqin.thirdparty.smsgc.util.SmsSender;
import jdbchelper.JdbcHelper;
import jdbchelper.MappingBatchFeeder;
import jdbchelper.QueryResult;

/**
 * 通用短信发送定时任务
 * 
 * @author mobs
 */
@Service
public class CommonSmsJob implements BaseJob {

	private static Logger logger = LoggerFactory.getLogger(CommonSmsJob.class);

	@Autowired
	private JobLogService joblogService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private SmsTmplService tmplService;

	/*
	 * 通用短信发送定时任务入口函数
	 */
	@Override
	public void execute(JobEntity jobEntity) {
		Date syncStartTime = new Date();
		int totalRecord = 0;
		int effectiveRecord = 0;
		boolean isHasError = false;
		JdbcHelper flexsmsDb = null;
		String batchId = null;

		try {
			// 1、生成本次批次号
			batchId = this.getRandomBatchNo();

			// 2、查询任务对应的抽数模板
			List<SmsTmpl> smsTmpls = tmplService.getByJobId(jobEntity.getId());

			// 3、 判断使用到的Company 和Department是固定的还是需要通过查询配置表数据
			String company = "";//公司
			String department = "";//部门
			Map<String, CompDept> companyDeptMap = null;

			//短信平台sqlserver数据源
			flexsmsDb = new JdbcHelper(DataSourceUtil.getDataSource("jdbc/smsDB"));

			/**
			 * feeType短信收费方式
			 * 1:按照sys_job表的company和department进行收费
			 * 2:通过cso从app_xq_dept4gd表中获取company和departmeng进行收费
			 */
			int feeType = jobEntity.getFeeType();
			if (feeType == 1) {
				company = jobEntity.getCompany();
				department = jobEntity.getDepartment();
			} else {
				companyDeptMap = getCompDeptMap(flexsmsDb);
			}

			/**
			 * sendSms:
			 * 1=插入短信平台库，同时需要发送
			 * 2=插入短信平台库，但不需要发送
			 * 3=不需要插入短信平台库
			 */
			int sendSms = jobEntity.getSendSms();
			logger.info("CommonSmsJob execute batchId:"+batchId+" feeType:"+feeType+" sendSms:"+sendSms+" company:"+company+" department:"+department);

			// 4、遍历抽数模板，使用SQL进行抽数并生成短信
			List<SmsInfo> smsList = new ArrayList<SmsInfo>();
			for (SmsTmpl smsTmpl : smsTmpls) {
				boolean buildResult = this.buildSms(jobEntity, batchId, smsTmpl, company, department, companyDeptMap, smsList);
				logger.info("buildSms tmplId:"+smsTmpl.getId()+" result:"+buildResult+" smsList size:"+smsList.size());
				if (!buildResult) {
					isHasError = true;
					break;
				}
			}

			List<SmsInfo> normalSmsList = new ArrayList<SmsInfo>();

			if (!isHasError) {

				// 5、保存数据
				if (smsList.size() > 0) {

					// 5.1 将短信日志保存到短信统一发送平台数据库
					smsService.insertBatch(smsList);

					// 5.2 过滤不符合条件的短信
					for (SmsInfo smsInfo : smsList) {
						if (!SmsInspector.check(smsInfo)) {
							continue;
						}
						normalSmsList.add(smsInfo);
					}

					totalRecord = smsList.size();
					effectiveRecord = normalSmsList.size();
					logger.info("send sms total size:"+totalRecord+" effectiveRecord size:"+effectiveRecord+" sendSms:"+sendSms);
					//5.3 如果把短信内容插入到短信平台接口表
					//5.3.1根据任务配置中的SEND_SMS判断是否需要发送短信
					if (sendSms != 3) {
						// 短信批量表短信发送状态标识。 0=需要发送；1=不需要发送
						int isNeedSendSms = 0;

						if (sendSms == 1) {
							isNeedSendSms = 0;
						} else if (sendSms == 2) {
							isNeedSendSms = 1;
						}
						logger.info("send sms isNeedSendSms:"+isNeedSendSms);
						if (effectiveRecord > 0) {
							// 5.3.2 将短信数据批量插入短信平台批量表
							isHasError = this.insertBatchSmsToMsgc(jobEntity.getName(), isHasError, flexsmsDb, normalSmsList, isNeedSendSms);
						}
					}
					// 5.4 发送成功，更新本批次短信统一发送平台数据库
					smsService.updateBatch(batchId, 1);
				}
			}

		} catch (Exception ex) {
			logger.error("[" + jobEntity.getName() + "]任务运行出错", ex);
			isHasError = true;
		}

		// 6、记录日志
		this.log(jobEntity, batchId, syncStartTime, totalRecord, effectiveRecord, isHasError);

		// 7、系统出错发送告警信息
		if (isHasError) {
			this.sendAlertMsg(jobEntity);
		}
	}

	/**
	 * 批量插入短信信息到短信平台中心批量表
	 * 
	 * @param jobEntity
	 * @param isHasError
	 * @param flexsmsDb
	 * @param normalSmsList
	 * @param isNeedSendSms
	 * @return
	 */
	private boolean insertBatchSmsToMsgc(String jobName, boolean isHasError, JdbcHelper flexsmsDb, List<SmsInfo> normalSmsList, int isNeedSendSms) {
		String insertBatchSmsSql = "insert into flex_send_rop(";
		insertBatchSmsSql += "userid,sendtime,phonecode,sendtext,company,departmet,smstype,";
		insertBatchSmsSql += "autoread,gwid,smscomcode,flagno,back_no,flag,qfflag,Chdrnum,Ind04,Ind05,create_date,status)";
		insertBatchSmsSql += "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'" + isNeedSendSms + "')";

		long beginTime = System.currentTimeMillis();
		logger.info("[" + jobName + "] insertBatchSmsToMsgc run sql:" + insertBatchSmsSql);

		try {
			flexsmsDb.beginTransaction();
			flexsmsDb.executeBatch(insertBatchSmsSql, new MappingBatchFeeder<SmsInfo>(normalSmsList.iterator(), Sendrop.getMapper()));
			flexsmsDb.commitTransaction();
		} catch (Exception ex) {
			flexsmsDb.rollbackTransaction();
			isHasError = true;
			logger.error("[" + jobName + "]向短信接口表插入批量短信失败", ex);
		}

		logger.info("[" + jobName + "] insertBatchSmsToMsgc run sql time:" + (System.currentTimeMillis() - beginTime) + "ms");

		return isHasError;
	}

	/**
	 * 发送告警信息
	 * @param jobEntity
	 */
	private void sendAlertMsg(JobEntity jobEntity) {
		//发送告警短信
		this.sendAlertSms(jobEntity);
		//发送告警邮件
		this.sendAlertEmail(jobEntity);
	}

	/**
	 * 发送告警邮件
	 * @param jobEntity
	 */
	private void sendAlertEmail(JobEntity jobEntity) {
		try {
			String emailReceiver = jobEntity.getEmailReceiver();
			String emailContent = jobEntity.getEmailContent();
			SendMail mailSender = new SendMail(SystemConfig.getPara("EmailHost"), SystemConfig.getPara("EmailUserName"), SystemConfig.getPara("EmailPassword"));
			// 发送email
			mailSender.send("[" + jobEntity.getName() + "]执行任务出错", emailContent, SystemConfig.getPara("EmailUserName"), emailReceiver);
		} catch (Exception e) {
			logger.error("[" + jobEntity.getName() + "]告警邮件发送失败",e);
		}
	}

	/**
	 * 发送告警短信
	 * @param jobEntity
	 */
	private void sendAlertSms(JobEntity jobEntity) {
		try {
			String smsReceiver = jobEntity.getSmsReceiver();
			String smsContent = jobEntity.getSmsContent();
			String[] smsReceivers = smsReceiver.split(",");
			for (int i = 0; i < smsReceivers.length; i++) {
				// 手机号码格式正确
				if (smsReceivers[i] != null || smsReceivers[i].length() == 11)
					SmsSender.send(smsReceivers[i], smsContent);// 发送短信
			}
		} catch (Exception e) {
			logger.error("sendAlertMsg error [" + jobEntity.getName() + "]告警邮件发送失败",e);
		}
	}

	/**
	 * 记录本次任务运行日志
	 * @param jobEntity
	 * @param syncStartTime
	 * @param staffCount
	 * @param isHasError
	 */
	private void log(JobEntity jobEntity, String batchId, Date syncStartTime, int totalNum, int successNum, boolean isHasError) {
		try {
			JobLog logData = new JobLog();
			logData.setJobId(jobEntity.getId());
			logData.setJobName(jobEntity.getName());
			logData.setBatchId(batchId);
			logData.setTotalNumber(totalNum);
			logData.setSuccessNumber(successNum);
			logData.setResult(isHasError ? 0 : 1);
			logData.setStartTime(syncStartTime);
			logData.setEndTime(new Date());
			logData.setCreateTime(new Date());
			logData.setUpdateTime(new Date());
			logData.setRemark(jobEntity.getName());
			joblogService.save(logData);
		} catch (Exception ex) {
			logger.error("保存任务[" + jobEntity.getName() + "]运行日志出错。", ex);
		}
	}

	/**
	 * 根据某一个模板生成短信数据
	 * @param jobEntity
	 * @param batchId
	 * @param smsTmpl
	 * @param company
	 * @param department
	 * @param companyDeptMap
	 * @param smsList
	 * @return
	 */
	private boolean buildSms(JobEntity jobEntity, String batchId, SmsTmpl smsTmpl, String company, String department, Map<String, CompDept> companyDeptMap, List<SmsInfo> smsList) {
		boolean buildResult = true;

		try {
			String smsTmplContent = smsTmpl.getSmsTmpl();
			String sqlTmplContent = smsTmpl.getSqlTmpl();

			// 4.1 替换抽数SQL中的schema
			sqlTmplContent = StringUtil.parse(sqlTmplContent, "\\$\\{(.+?)\\}");

			// 4.2 初始化数据库连接，根据模板SQL查询数据
			List<Map<String, Object>> dataRecordList = queryBizData(jobEntity.getName(), smsTmpl, sqlTmplContent);

			Integer recordSize = CollectionUtils.isNotEmpty(dataRecordList) ? dataRecordList.size() : 0;
			logger.info("buildSms " + jobEntity.getName() +" tmplId:"+ smsTmpl.getId()+ " queryBizData size:" + recordSize );

			// 4、替换短信模板中变量，生成短信
			String placeHolderTag = null;
			String placeHolderNotNullTag = null;
			String gwid = DateUtil.formatDate(DateUtil.PATTERN_YYYYMMDDNOBAR, new Date()) + jobEntity.getGwid();

			/**
			 * FeeType短信收费方式
			 * 1:按照sys_job表的company和department进行收费
			 * 2:通过cso从app_xq_dept4gd表中获取company和departmeng进行收费
			 */
			int feeType = jobEntity.getFeeType();

			long job_id = jobEntity.getId();//job编号
			int tmplId = smsTmpl.getId();//模板编号
			int orderNo = smsTmpl.getOrderNo();//模板序号
			String smsType = jobEntity.getSmsType();//短信类别代码	2、 代理人服务
			String autoread = jobEntity.getAutoread();//短信处理级别代码	3、营运知会
			String flagNo = jobEntity.getFlagNo();//短信业务类型 ,单独分配：0060
			String shortNum = jobEntity.getShortNum();//发送短信的短号
			String userId = jobEntity.getUserId();//用户id:DSPT
			logger.info("buildSms job_id:"+job_id+" tmplId:"+tmplId+" orderNo:"+orderNo+" smsType:"+smsType+" autoread:"+autoread+" flagNo:"+flagNo
					+" shortNum:"+shortNum+" userId:"+userId);

			for (Map<String, Object> mapItem : dataRecordList) {

				// 手机号
				String mobilePhone = null;
				String cso = null;
				// 保单号
				String chdrnum = null;

				// 短信模板
				String smsContent = smsTmplContent;

				boolean occureNullValue = false;
				// 遍历该行记录每列字段值
				for (String columnName : mapItem.keySet()) {
					Object columnValue = mapItem.get(columnName);
					String placeHolderValue = (columnValue == null ? "" : ((String) columnValue).trim());

					placeHolderTag = "@{" + columnName + "}";
					placeHolderNotNullTag = "${" + columnName + "}";

					// 4.1 先判断可空占位符是否存在，并对占位符内容进行替换
					if (smsContent.indexOf(placeHolderTag) > -1) {
						smsContent = smsContent.replace(placeHolderTag, placeHolderValue);
					}

					// 4.2 再判断不可空占位符是否存在，并对占位符内容进行替换
					if (smsContent.indexOf(placeHolderNotNullTag) > -1) {
						smsContent = smsContent.replace(placeHolderNotNullTag, placeHolderValue);

						if (StringUtils.isBlank(placeHolderValue)) {
							occureNullValue = true;
						}
					}

					if ("mobile".equalsIgnoreCase(columnName)) {
						mobilePhone = (String) mapItem.get(columnName);

						if (StringUtils.isBlank(mobilePhone)) {
							occureNullValue = true;
						} else {
							mobilePhone = mobilePhone.trim();
						}
					}

					if ("cso".equalsIgnoreCase(columnName)) {
						cso = (String) mapItem.get(columnName);
					}

					if ("chdrnum".equalsIgnoreCase(columnName)) {
						chdrnum = (String) mapItem.get(columnName);
						// 如果为NULL，则设置为空字符串
						if (StringUtils.isBlank(chdrnum)) {
							chdrnum = "";
						}
					}

				}

				// 拼装短信信息
				SmsInfo smsInfo = new SmsInfo();
				smsInfo.setMobile(mobilePhone); // 手机号
				smsInfo.setContent(smsContent); // 短信内容
				smsInfo.setJobId(job_id); // 定时任务编号
				smsInfo.setSmsTmplId(tmplId); // 关联的抽数模板编号
				smsInfo.setBatchId(batchId); // 生成短信的批次号

				// 处理Company,Department
				if (feeType == 1) {
					smsInfo.setCompany(company);
					smsInfo.setDepartment(department);
				} else {
					CompDept compDept = companyDeptMap.get(cso);
					if (compDept != null) {
						smsInfo.setCompany(compDept.getCompany());
						smsInfo.setDepartment(compDept.getDepartment());
					}
				}

				smsInfo.setSmsType(smsType);
				smsInfo.setAutoread(autoread);
				smsInfo.setGwid(gwid);
				smsInfo.setFlagNo(flagNo);
				smsInfo.setChdrnum(chdrnum);
				smsInfo.setOrderNo(orderNo);
				smsInfo.setShortNum(shortNum);
				smsInfo.setUserId(userId);

				// 如果出现参数为空的情况，该条短信只生成不发送
				if (occureNullValue)
					smsInfo.setStatusId(2); // 状态 0=未发送;1=已发送，2=无效短信
				else
					smsInfo.setStatusId(0); // 状态 0=未发送;1=已发送，2=无效短信

				smsInfo.setSendStartDate(null); // 短信发送开始日期
				smsInfo.setSendStartTime(null); // 短信发送开始时间
				smsInfo.setSendEndDate(null); // 短信发送结束日期
				smsInfo.setSendEndTime(null); // 短信发送结束时间
				smsInfo.setSendTime(null); // 发送时间
				smsInfo.setCreateTime(new Date()); // 创建时间
				smsInfo.setUpdateTime(null); // 更新时间

				smsList.add(smsInfo);
			}
		} catch (Exception ex) {
			logger.error("解析记录生成短信时出错", ex);
			buildResult = false;
		}

		return buildResult;
	}

	/**
	 * 查询业务数据
	 * @param jobEntity
	 * @param smsTmpl
	 * @param sqlTmplContent
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> queryBizData(String jobName, SmsTmpl smsTmpl, String sqlTmplContent) throws Exception {
		long beginTime = System.currentTimeMillis();

		logger.info("[" + jobName + "] queryBizData run sql:" + sqlTmplContent);

		JdbcHelper bizDb = new JdbcHelper(DataSourceUtil.getDataSource(smsTmpl.getDataSource()));
		List<Map<String, Object>> dataRecordList = bizDb.queryForList(sqlTmplContent, new ListColumnMapBeanCreator());

		logger.info("[" + jobName + "] queryBizData run sql time:" + (System.currentTimeMillis() - beginTime) + "ms");

		return dataRecordList;
	}

	/**
	 * 生成随机批次号
	 * @return
	 */
	private String getRandomBatchNo() {
		Random random = new Random();
		String randomNum = StringUtils.leftPad(String.valueOf(random.nextInt(10000)), 5, "0");
		String batchId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + randomNum;
		return batchId;
	}

	/**
	 * 获取短信平台中心短信配置信息表数据
	 * 
	 * @param flexsmsDb
	 * @return
	 */
	private Map<String, CompDept> getCompDeptMap(JdbcHelper flexsmsDb) {
		Map<String, CompDept> companyDeptMap;
		// 根据任务配置要求，对发送短信时需要根据保单号所属的CSO，决定Company和Department
		QueryResult queryResult = flexsmsDb.query("select chdrcoy,smscomcode ,departno from app_xq_dept4gd");
		queryResult.setAutoClose(true);
		companyDeptMap = new HashMap<String, CompDept>();

		while (queryResult.next()) {
			String cso = queryResult.getString("chdrcoy");
			String smsComCode = queryResult.getString("smscomcode");
			String departNo = queryResult.getString("departno");

			CompDept compDept = new CompDept();
			compDept.setCso(cso);
			compDept.setCompany(smsComCode);
			compDept.setDepartment(departNo);
			companyDeptMap.put(cso, compDept);
		}
		return companyDeptMap;
	}
}
