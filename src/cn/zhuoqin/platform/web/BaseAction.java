package cn.zhuoqin.platform.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import cn.zhuoqin.platform.page.Page;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,Preparable {
	public final static String EXPORT = "export";
	protected T entity;
	protected Long id;
	protected String idstr;
	protected String[] ids;
	protected Page<T> page = new Page<T>();
	protected AjaxResData retData = new AjaxResData();
	private static final long serialVersionUID = 1L;
	
	public Logger logger = LoggerFactory.getLogger(BaseAction.class);

	 public abstract String save() throws Exception;

	 public abstract String delete()  throws Exception;
	 
	 public abstract String update()  throws Exception;
	 
	 public void prepare() throws Exception{}

	 public void prepareInput() throws Exception {
		 prepareModel();
	 }
     public void prepareSave() throws Exception{
			    prepareModel();
	 }
     protected abstract void prepareModel()  throws Exception;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		if(StringUtils.isBlank(idstr)){
			this.idstr = null;
		}
		if (!idstr.contains(",")){
	        setId(Long.valueOf(Long.parseLong(idstr)));
		}
	     this.ids = idstr.trim().split(",");
	}

	/**
	 * 初始化分页数据
	 * 
	 * @param filter
	 * @return
	 */
	protected Map<String, Object> initPage(Map<String, Object> filter) {
		String pageNo = (String) filter.get("pageNo");
		String pageSize = (String) filter.get("pageSize");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "1";
		}
		if (StringUtils.isEmpty(pageSize)) {
			pageSize = "10";
		}
		int pageFrom = (Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize);
		int pageTo = Integer.parseInt(pageNo) * Integer.parseInt(pageSize);
		filter.put("pageFrom", pageFrom);
		filter.put("pageTo", pageTo);
		return filter;
	}
	 
}
