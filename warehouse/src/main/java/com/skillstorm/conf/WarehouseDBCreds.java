package com.skillstorm.conf;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class WarehouseDBCreds {
	
	private static WarehouseDBCreds instance;
	private String url;
	private String username;
	private String password;
	
	private WarehouseDBCreds() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (InputStream input = WarehouseDBCreds.class.getClassLoader().getResourceAsStream("application.properties")){
				Properties props = new Properties();
				props.load(input);
				this.url = props.getProperty("db.url");
				this.username = props.getProperty("db.username");
				this.password = props.getProperty("db.password");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	public static WarehouseDBCreds getInstance() {
		if (instance == null) {
			instance = new WarehouseDBCreds();
		}
		return instance;
	}
	
	public String getUrl() {return url;}
	
	public String getUsername() {return username;}
	
	public String getPassword() {return password;}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
