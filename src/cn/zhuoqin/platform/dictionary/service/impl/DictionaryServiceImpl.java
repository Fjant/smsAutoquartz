package cn.zhuoqin.platform.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuoqin.platform.dictionary.dao.DictionaryDao;
import cn.zhuoqin.platform.dictionary.model.Dictionary;
import cn.zhuoqin.platform.dictionary.service.DictionaryService;

@Service(value="dictionaryService")

public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Override
	public Dictionary getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Dictionary entity) {
		// TODO Auto-generated method stub
		dictionaryDao.save(entity);
	}

	@Override
	public void removeById(long id) {
		dictionaryDao.removeById(id);	
	}
	
	@Override
	public void updateNameById(Dictionary dictionary) {
		dictionaryDao.updateNameById(dictionary);
	}

	@Override
	public  Dictionary findDictionary(Map<String, Object> filter) {
		return this.dictionaryDao.findDictionary(filter);
	}
	@Override
	public  Dictionary findDictionaryById(String  id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return this.dictionaryDao.findDictionary(map);
	}
	@Override
	public List<Dictionary> selectDictionary(Map<String, Object> filter) {
		return this.dictionaryDao.selectDictionary(filter);
	}
	@Override
	public Dictionary uniqueDictionary(Map<String, Object> filter) {
		return this.dictionaryDao.uniqueDictionary(filter);
	}

}
