package cn.zhuoqin.platform.msg.model;

import java.util.Date;

import cn.zhuoqin.platform.ibatis.BaseEntity;



public class Template extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7212824510594279071L;

	/**
	 * 编号
	 * */
	private long id;
	
	/**
	 * 模板名称
	 * */
	private String name;
	
	/**
	 * 模板内容
	 * */
	private String content;
	
	/**
	 * 创建者（各子系统应用）
	 * */
	private String createBy;
	
	/**
	 * 创建时间
	 * */
	private Date createTime;
	
	/**
	 * 更新时间
	 * */
	private Date updateTime;
	/**
	 * 用户id
	 * @author fej
	 * 2018/3/1
	 */
	private String userId;
	
	/**
	 * 电话号码
	 * @author fej
	 * 2018/3/1
	 */
	private String mobile;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
