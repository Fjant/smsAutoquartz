package cn.zhuoqin.platform.jdbc;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

public class DataSourceUtil {
	private static Hashtable<String, DataSource> dataSources = new Hashtable<String, DataSource>();
	private static Object lockObject = new Object();

	public static DataSource getDataSource(String dataSourceName) throws Exception {

		if (StringUtils.isBlank(dataSourceName)) {
			throw new IllegalArgumentException("未指定数据源名称");
		}
		DataSource dataSource = null;
		String lowerDSName = dataSourceName.toLowerCase();
		if (dataSources == null || dataSources.size() == 0 || !dataSources.containsKey(lowerDSName) || dataSources.get(lowerDSName) == null) {
			dataSource = createDataSource(dataSourceName);
			dataSources.put(lowerDSName, dataSource);

			return dataSource;
		}

		return dataSources.get(lowerDSName);
	}

	public static DataSource createDataSource(String dataSourceName) throws IOException, NamingException {
		synchronized (lockObject) {
			Context initCtx = new InitialContext();
			return (DataSource) initCtx.lookup("java:comp/env/" + dataSourceName);

			// return (DataSource) SpringUtil.getBean(dataSourceName);
		}
	}
}
