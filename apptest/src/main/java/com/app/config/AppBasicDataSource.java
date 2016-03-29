package com.app.config;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;

public class AppBasicDataSource extends BasicDataSource {

	public AppBasicDataSource(String driverClass, String url, String username, String password) {
		super.setDriverClassName(driverClass);
		super.setUrl(url);
		super.setUsername(username);
		super.setPassword(password);
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	public void close() throws SQLException {
		super.close();
	}
}
