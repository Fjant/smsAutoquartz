package cn.zhuoqin.platform.dictionary.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhuoqin.platform.dictionary.model.Dictionary;


public interface DictionaryService {
	Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	
	public Dictionary getById(Long id);
		
	public void save(Dictionary entity);

	public void removeById(long id);
	
	public void updateNameById(Dictionary dictionary);

	public Dictionary findDictionary(Map<String, Object> filter);
	
	public Dictionary uniqueDictionary(Map<String, Object> filter);
	
	public Dictionary findDictionaryById(String id);
	
	public List<Dictionary> selectDictionary(Map<String, Object> filter);
	


	
}
