package cn.zhuoqin.thirdparty.smsgc.jdbchelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jdbchelper.BeanCreator;

public class ListColumnMapBeanCreator implements BeanCreator<Map<String, Object>> {

	@Override
	public Map<String, Object> createBean(ResultSet resultSet) throws SQLException {
		Map<String, Object> items = new HashMap<String, Object>();
		ResultSetMetaData rsmd = resultSet.getMetaData();

		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			String columnName = rsmd.getColumnName(i + 1);
			items.put(columnName, resultSet.getObject(columnName));
		}

		return items;
	}
}
