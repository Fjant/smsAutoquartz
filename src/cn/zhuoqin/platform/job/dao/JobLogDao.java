package cn.zhuoqin.platform.job.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zhuoqin.platform.ibatis.MyBatisEntityDao;
import cn.zhuoqin.platform.job.model.JobLog;

@Repository
public class JobLogDao extends MyBatisEntityDao<JobLog, Long> {
	public List<JobLog> getByPage(Map<String, Object> filters) throws Exception {
		return this.getSqlSessionTemplate().selectList("JobLog.getByPage", filters);
	}

	public int count(Map<String, Object> filters) throws Exception {
		return (Integer) this.getSqlSessionTemplate().selectOne("JobLog.count", filters);
	}
}
