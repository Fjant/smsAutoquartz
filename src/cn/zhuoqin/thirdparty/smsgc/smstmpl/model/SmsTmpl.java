package cn.zhuoqin.thirdparty.smsgc.smstmpl.model;

import java.util.Date;

/**
 * 短信模板实体
 * 
 * @author mobs
 *
 */
public class SmsTmpl {

	private static final long serialVersionUID = 1L;

	/**
	 * 模板编号
	 */
	private int id;
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * SQL抽数脚本
	 */
	private String sqlTmpl;
	/**
	 * 短信内容模板
	 */
	private String smsTmpl;
	/**
	 * 模板顺序
	 */
	private int orderNo;
	/**
	 * 定时任务编号
	 */
	private int jobId;
	/**
	 * JNDI数据源名称
	 */
	private String dataSource;

	/**
	 * 状态
	 */
	private Integer statusId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSqlTmpl() {
		return sqlTmpl;
	}

	public void setSqlTmpl(String sqlTmpl) {
		this.sqlTmpl = sqlTmpl;
	}

	public String getSmsTmpl() {
		return smsTmpl;
	}

	public void setSmsTmpl(String smsTmpl) {
		this.smsTmpl = smsTmpl;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
