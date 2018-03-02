package cn.zhuoqin.platform.job.model;

import java.util.Date;

import cn.zhuoqin.platform.ibatis.BaseEntity;

public class JobEntity extends BaseEntity {
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_NOT_RUNNING = 0;
	public static final int CONCURRENT_IS = 1;
	public static final int CONCURRENT_NOT = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private long id;

	/**
	 * 任务名称
	 */
	private String name;

	/**
	 * 任务类型
	 */
	private int incTypeId;

	/**
	 * 任务组
	 */
	private String group;

	/**
	 * 任务接口类
	 */
	private String classPath;

	/**
	 * 任务接口函数
	 */
	private String method;

	/**
	 * 任务接口参数
	 */
	private String argument;

	/**
	 * 任务接口参数
	 */
	private int isConcurrent;

	/**
	 * 任务运行时间
	 */
	private String cronExpression;

	/**
	 * 任务状态
	 */
	private int statusId;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 告警短信接收者
	 */
	private String smsReceiver;
	/**
	 * 告警短信内容
	 */
	private String smsContent;
	/**
	 * 告警邮件接收者
	 */
	private String emailReceiver;
	/**
	 * 告警邮件内容
	 */
	private String emailContent;
	/**
	 * 是否发送短信标识
	 */
	private int sendSms;
	/**
	 * 短信发送开始日期
	 */
	private Date sendStartDate;
	/**
	 * 短信发送开始时间
	 */
	private String sendStartTime;
	/**
	 * 短信发送结束日期
	 */
	private Date sendEndDate;
	/**
	 * 短信发送结束时间
	 */
	private String sendEndTime;

	/**
	 * 发送机构
	 */
	private String company;
	/**
	 * 发送部门
	 */
	private String department;
	/**
	 * 计费类型
	 */
	private Integer feeType;
	/**
	 * 短信类别代码
	 */
	private String smsType;
	/**
	 * 短信处理级别代码
	 */
	private String autoread;
	/**
	 * 批次号
	 */
	private String gwid;
	/**
	 * 短信业务类型
	 */
	private String flagNo;

	/**
	 * 短号
	 */
	private String shortNum;

	/**
	 * 短信发送人
	 */
	private String userId;

	/**
	 * 创建者
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIncTypeId() {
		return incTypeId;
	}

	public void setIncTypeId(int incTypeId) {
		this.incTypeId = incTypeId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public int getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(int isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSmsReceiver() {
		return smsReceiver;
	}

	public void setSmsReceiver(String smsReceiver) {
		this.smsReceiver = smsReceiver;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public int getSendSms() {
		return sendSms;
	}

	public void setSendSms(int sendSms) {
		this.sendSms = sendSms;
	}

	public Date getSendStartDate() {
		return sendStartDate;
	}

	public void setSendStartDate(Date sendStartDate) {
		this.sendStartDate = sendStartDate;
	}

	public String getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public Date getSendEndDate() {
		return sendEndDate;
	}

	public void setSendEndDate(Date sendEndDate) {
		this.sendEndDate = sendEndDate;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getAutoread() {
		return autoread;
	}

	public void setAutoread(String autoread) {
		this.autoread = autoread;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getFlagNo() {
		return flagNo;
	}

	public void setFlagNo(String flagNo) {
		this.flagNo = flagNo;
	}

	public String getShortNum() {
		return shortNum;
	}

	public void setShortNum(String shortNum) {
		this.shortNum = shortNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
