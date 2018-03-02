package cn.zhuoqin.platform.dictionary.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zhuoqin.platform.dictionary.model.Dictionary;
import cn.zhuoqin.platform.ibatis.MyBatisEntityDao;

/**
 * 
 * @author Grace
 * @since 2015-10-27
 *
 */
@Repository
public class DictionaryDao extends MyBatisEntityDao<Dictionary, Long> {

	@Override
	public void save(Object paramObject){
		super.save(paramObject);
	}
	public void removeById(long id) {
		getSqlSessionTemplate().delete("Dictionary.deleteByPrimaryKey", id);
	}
	public void updateNameById(Dictionary dictionary) {
		getSqlSessionTemplate().update("Dictionary.updateItemNameChn", dictionary);
	}	
	public Dictionary findDictionary(Map<String, Object> filter) {
		return (Dictionary) getSqlSessionTemplate().selectOne("Dictionary.findDictionary", filter);
	}
	public Dictionary uniqueDictionary(Map<String, Object> filter) {
		return (Dictionary) getSqlSessionTemplate().selectOne("Dictionary.uniqueDictionary", filter);
	}
	@SuppressWarnings("unchecked")
	public List<Dictionary> selectDictionary(Map<String, Object> filter) {
		List<Dictionary> projects = this.getSqlSessionTemplate().selectList("Dictionary.selectDictionary", filter);
		return projects;
	}
}

