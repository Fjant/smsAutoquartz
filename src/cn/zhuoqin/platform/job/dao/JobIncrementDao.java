package cn.zhuoqin.platform.job.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zhuoqin.platform.ibatis.MyBatisEntityDao;
import cn.zhuoqin.platform.job.model.JobIncrement;
@Repository
public class JobIncrementDao extends MyBatisEntityDao<JobIncrement, Long> {
	public List<JobIncrement> getByPage(Map<String, Object> filters) throws Exception {
		return this.getSqlSessionTemplate().selectList("JobIncrement.getByPage", filters);
	}

	public int count(Map<String, Object> filters) throws Exception {
		return (Integer) this.getSqlSessionTemplate().selectOne("JobIncrement.count", filters);
	}
	
	public JobIncrement getByTypeId(int typeId) throws Exception {
		return (JobIncrement) this.getSqlSessionTemplate().selectOne("JobIncrement.getByTypeId", typeId);
	}
}
