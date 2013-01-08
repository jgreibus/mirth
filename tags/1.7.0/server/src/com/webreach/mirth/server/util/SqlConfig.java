package com.webreach.mirth.server.util;

import java.io.Reader;
import java.util.Properties;

import com.ibatis.common.logging.LogFactory;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.webreach.mirth.server.controllers.ConfigurationController;
import com.webreach.mirth.util.PropertyLoader;

public class SqlConfig {
	private static Properties mirthProperties = PropertyLoader.loadProperties("mirth");
	private static final SqlMapClient sqlMap;

	static {
		try {
			String database = PropertyLoader.getProperty(mirthProperties, "database");
			String resource = database + System.getProperty("file.separator") + database + "-SqlMapConfig.xml";
			LogFactory.selectLog4JLogging();
			Reader reader = Resources.getResourceAsReader(resource);
			if (database.equalsIgnoreCase("derby")) {
				Properties props = PropertyLoader.loadProperties(database + "-SqlMapConfig");
				props.setProperty("mirthHomeDir", ConfigurationController.mirthHomeDir);
				sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader, props);
			} else {			
				sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}
}