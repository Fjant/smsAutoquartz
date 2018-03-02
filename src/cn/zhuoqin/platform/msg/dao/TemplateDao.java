package cn.zhuoqin.platform.msg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zhuoqin.platform.ibatis.MyBatisEntityDao;
import cn.zhuoqin.platform.msg.model.Template;



@Repository
public class TemplateDao extends MyBatisEntityDao<Template, Long> {
		
	public List<Template> getList(Map<String, Object> filters) throws Exception{
		return this.getSqlSessionTemplate().selectList("Template.getList", filters);
	}
	
	public List<Template> getByPage(Map<String, Object> filters) throws Exception {
		return this.getSqlSessionTemplate().selectList("Template.getByPage", filters);
	}

	public int count(Map<String, Object> filters) throws Exception {
		return (Integer) this.getSqlSessionTemplate().selectOne("Template.count", filters);
	}
	
	public void deleteById(Long id) throws Exception{
		getSqlSessionTemplate().delete("Template.deleteByPrimaryKey", id);
	}
}
