package cn.zhuoqin.platform.job.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import cn.zhuoqin.platform.job.model.JobLog;
import cn.zhuoqin.platform.job.service.JobLogService;
import cn.zhuoqin.platform.util.JsonUtil;
import cn.zhuoqin.platform.util.Struts2Util;
import cn.zhuoqin.platform.web.BaseAction;


@Namespace("/job")
@Action(value = "joblog")
@Results({ @Result(name = BaseAction.SUCCESS, type = "json") })
public class JobLogAction extends BaseAction<JobLog>{

	private static final long serialVersionUID = 830849477910566761L;

	@Autowired
	private JobLogService jobLogService;
	
	public String list(){
		try {
			Map<String, Object> filter = WebUtils.getParametersStartingWith(Struts2Util.getRequest(), "search_");
		    filter = initPage(filter);
		    List<JobLog> job = jobLogService.getByPage(filter);
		    int count = jobLogService.count(filter);
		    page.setResult(job);
		    page.setTotalCount(count);
		} catch (Exception e) {
			logger.error("查询任务记录列表异常",e);
			e.printStackTrace();
		}finally{
			JsonUtil.renderJson(page);
		}
		return SUCCESS;
	}
	@Override
	public JobLog getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
