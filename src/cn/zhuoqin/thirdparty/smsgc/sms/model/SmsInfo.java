package cn.zhuoqin.thirdparty.smsgc.sms.model;

import java.util.Date;

/**
 * @author mobs
 *
 */
public class SmsInfo {
	private int id;
	private String mobile;
	private String content;
	private Long jobId;
	private int smsTmplId;
	private String batchId;

	/**
	 * 保单号
	 */
	private String chdrnum;
	/**
	 * 发送机构
	 */
	private String company;
	/**
	 * 发送部门
	 */
	private String department;
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
	 * 执行的SQL的排序
	 */
	private Integer orderNo;

	/**
	 * 短号
	 */
	private String shortNum;

	/**
	 * 短信发送人
	 */
	private String userId;

	private Integer statusId;
	private Date sendStartDate;
	private String sendStartTime;
	private Date sendEndDate;
	private String sendEndTime;
	private Date createTime;
	private String createBy;
	private Date sendTime;
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public int getSmsTmplId() {
		return smsTmplId;
	}

	public void setSmsTmplId(int smsTmplId) {
		this.smsTmplId = smsTmplId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getChdrnum() {
		return chdrnum;
	}

	public void setChdrnum(String chdrnum) {
		this.chdrnum = chdrnum;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
