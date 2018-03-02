package cn.zhuoqin.platform.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CollectionsUtil {
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {
			@Override
			public int compare(String key1, String key2) {

				return key1.compareTo(key2);
			}
		});

		sortMap.putAll(map);

		return sortMap;
	}
}
