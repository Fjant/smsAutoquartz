package cn.zhuoqin.platform.dictionary.model;
/**
 * 界面的字典数据显示类
 * @author Grace
 * @since 2015-10-28
 */
public class ZTree {
	private String dicId;//自增id
	private Long id;//字典item_value
	private Long pId;//字典上级Id
	private String name;//字典中文名
	private boolean isParent;//是不是有下级(注意isParent的get，set方法。一定是要getIsParent()、setIsParent())
	private Long dataType;//字典类型
	
	public ZTree() {
		super();
	}	
	public ZTree(String dicId, Long id, Long pId, String name, boolean isParent, Long dataType) {
		super();
		this.dicId = dicId;
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.isParent = isParent;
		this.dataType = dataType;
	}
	public String getDicId() {
		return dicId;
	}
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public Long getDataType() {
		return dataType;
	}
	public void setDataType(Long dataType) {
		this.dataType = dataType;
	}
	
}
