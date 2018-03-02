package cn.zhuoqin.thirdparty.smsgc.smstmpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuoqin.thirdparty.smsgc.smstmpl.dao.SmsTmplDao;
import cn.zhuoqin.thirdparty.smsgc.smstmpl.model.SmsTmpl;
import cn.zhuoqin.thirdparty.smsgc.smstmpl.service.SmsTmplService;

@Service
public class SmsTmplServiceImpl implements SmsTmplService {

	@Autowired
	private SmsTmplDao smsTmplDao;

	/*
	 * 通过job_id获得与任务相关的短信与SQL模板信息
	 */
	@Override
	public List<SmsTmpl> getByJobId(long jobId) throws Exception {

		return smsTmplDao.getByJobId(jobId);
	}
}
