package cn.zhuoqin.thirdparty.smsgc.smstmpl.service;

import java.util.List;

import cn.zhuoqin.thirdparty.smsgc.smstmpl.model.SmsTmpl;

public interface SmsTmplService {

	/**
	 * 通过job_id获得与任务相关的短信与SQL模板信息
	 * 
	 * @param jobid
	 * @return
	 * @throws Exception
	 */
	public List<SmsTmpl> getByJobId(long jobid) throws Exception;
}
