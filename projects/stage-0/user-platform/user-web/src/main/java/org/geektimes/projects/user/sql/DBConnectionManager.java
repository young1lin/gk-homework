package org.geektimes.projects.user.sql;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionManager {

	private final Logger logger = Logger.getLogger(DBConnectionManager.class.getName());

	@Resource(name = "dataSource")
	private DataSource dataSource;

	public DBConnectionManager() { }

	public Connection getConnection() {
		// 依赖查找
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		}
		catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		if (connection != null) {
			logger.log(Level.INFO, "获取 JNDI 数据库连接成功！");
		}
		return connection;
	}



	public void releaseConnection() {
	}

	public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE users";

	public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
			"id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"name VARCHAR(16) NOT NULL, " +
			"password VARCHAR(64) NOT NULL, " +
			"email VARCHAR(64) NOT NULL, " +
			"phoneNumber VARCHAR(64) NOT NULL" +
			")";

	public static final String INSERT_USER_DML_SQL = "INSERT INTO users(name,password,email,phoneNumber) VALUES " +
			"('A','******','a@gmail.com','1') , " +
			"('B','******','b@gmail.com','2') , " +
			"('C','******','c@gmail.com','3') , " +
			"('D','******','d@gmail.com','4') , " +
			"('E','******','e@gmail.com','5')";

	private static String mapColumnLabel(String fieldName) {
		return fieldName;
	}

	/**
	 * 数据类型与 ResultSet 方法名映射
	 */
	static Map<Class, String> typeMethodMappings = new HashMap<>();

	static {
		typeMethodMappings.put(Long.class, "getLong");
		typeMethodMappings.put(String.class, "getString");
	}

}
