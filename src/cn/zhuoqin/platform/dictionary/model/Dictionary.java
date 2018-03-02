package cn.zhuoqin.platform.dictionary.model;

import java.sql.Timestamp;

import cn.zhuoqin.platform.ibatis.BaseEntity;


/**
 * 数据字典基础表
 * 
 * @author Grace
 * @since 2015-10-27
 *
 */
public class Dictionary extends BaseEntity{

	private static final long serialVersionUID = 4643412130854488330L;
	private Long id;
	private String itemNameEn;// 英文值	
	private String itemNameChnTc;//文本信息-繁体
	private String itemNameChnSc;//文本信息-简体
	private Long itemValue;// item值
	private Long itemLevel;// item的等级
	private Long parentId;//上级ID
	private Long firstLevelId;// 第一等级的ID
	private Long seconeLevelId;// 第二等级的ID
	private Long thirdLevelId;// 第三等级的ID
	private Long forthLevelId;// 第四等级的ID
	private Integer sortNo;// 排序
	private Long dataType;// 字典类型
	private String comments;// 备注
	private Timestamp createTime;// 创建时间

	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dictionary(String itemNameChnTc,String itemNameChnSc, String itemNameEn, Long itemValue, Long itemLevel, Long parentId, Long dataType, String comments, Timestamp createTime) {
		super();
		this.itemNameChnTc = itemNameChnTc;
		this.itemNameChnSc = itemNameChnSc;
		this.itemNameEn = itemNameEn;
		this.itemValue = itemValue;
		this.itemLevel = itemLevel;
		this.parentId = parentId;
		this.dataType = dataType;
		this.comments = comments;
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemNameEn() {
		return itemNameEn;
	}
	public void setItemNameEn(String itemNameEn) {
		this.itemNameEn = itemNameEn;
	}
	public Long getItemValue() {
		return itemValue;
	}
	public void setItemValue(Long itemValue) {
		this.itemValue = itemValue;
	}
	public Long getItemLevel() {
		return itemLevel;
	}
	public void setItemLevel(Long itemLevel) {
		this.itemLevel = itemLevel;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getFirstLevelId() {
		return firstLevelId;
	}
	public void setFirstLevelId(Long firstLevelId) {
		this.firstLevelId = firstLevelId;
	}
	public Long getSeconeLevelId() {
		return seconeLevelId;
	}
	public void setSeconeLevelId(Long seconeLevelId) {
		this.seconeLevelId = seconeLevelId;
	}
	public Long getThirdLevelId() {
		return thirdLevelId;
	}
	public void setThirdLevelId(Long thirdLevelId) {
		this.thirdLevelId = thirdLevelId;
	}
	public Long getForthLevelId() {
		return forthLevelId;
	}
	public void setForthLevelId(Long forthLevelId) {
		this.forthLevelId = forthLevelId;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Long getDataType() {
		return dataType;
	}
	public void setDataType(Long dataType) {
		this.dataType = dataType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getItemNameChnTc() {
		return itemNameChnTc;
	}
	public void setItemNameChnTc(String itemNameChnTc) {
		this.itemNameChnTc = itemNameChnTc;
	}
	public String getItemNameChnSc() {
		return itemNameChnSc;
	}
	public void setItemNameChnSc(String itemNameChnSc) {
		this.itemNameChnSc = itemNameChnSc;
	}
	

}
