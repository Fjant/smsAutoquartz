package cn.zhuoqin.thirdparty.smsgc.smstmpl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zhuoqin.platform.ibatis.MyBatisEntityDao;
import cn.zhuoqin.thirdparty.smsgc.smstmpl.model.SmsTmpl;

@Repository
public class SmsTmplDao extends MyBatisEntityDao<SmsTmpl, Long> {

	/**
	 * 通过任务编号获取与任务相关的短信模板与SQL取数模板
	 * 
	 * @param jobid
	 * @return
	 * @throws Exception
	 */
	public List<SmsTmpl> getByJobId(long jobId) throws Exception {
		return this.getSqlSessionTemplate().selectList("SmsTmpl.getByJobId", jobId);
	}
}
