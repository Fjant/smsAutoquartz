package cn.zhuoqin.platform.job.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuoqin.platform.job.dao.JobLogDao;
import cn.zhuoqin.platform.job.model.JobLog;
import cn.zhuoqin.platform.job.service.JobLogService;

@Service
public class JobLogServiceImpl implements JobLogService {

	@Autowired
	private JobLogDao jobLogDao;

	@Override
	public void save(JobLog entity) throws Exception {
		jobLogDao.insert(entity);
	}

	@Override
	public JobLog getById(long id) throws Exception {
		return jobLogDao.get(id);
	}

	@Override
	public void update(JobLog entity) throws Exception {
		jobLogDao.update(entity);
	}

	@Override
	public List<JobLog> getAll() throws Exception {
		return jobLogDao.getAll();
	}

	@Override
	public List<JobLog> getByPage(Map<String, Object> filters) throws Exception {
		return jobLogDao.getByPage(filters);
	}

	@Override
	public int count(Map<String, Object> filters) throws Exception {
		return jobLogDao.count(filters);
	}
}
