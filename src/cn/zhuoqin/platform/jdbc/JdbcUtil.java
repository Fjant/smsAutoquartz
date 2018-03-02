package cn.zhuoqin.platform.jdbc;

import jdbchelper.JdbcHelper;

public class JdbcUtil {

	public static JdbcHelper getHelper(String dbSource) throws Exception {
		return new JdbcHelper(DataSourceUtil.getDataSource(dbSource));
	}
}
