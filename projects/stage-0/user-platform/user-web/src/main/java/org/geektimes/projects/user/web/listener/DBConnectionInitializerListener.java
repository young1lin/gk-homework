package org.geektimes.projects.user.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class DBConnectionInitializerListener implements ServletContextListener {

	private Server server = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			System.out.println("正在启动h2...");
			server = Server.createTcpServer(
					new String[] {"-tcp", "-tcpAllowOthers", "-tcpPort",
							"8043"}).start();
			System.out.println("启动成功：" + server.getURL());
		}
		catch (SQLException e) {
			System.out.println("启动h2出错：" + e.toString());

			e.printStackTrace();
			throw new RuntimeException(e);

		}
		Connection conn = null;
		Statement stat = null;
		ResultSet resultSet = null;
		try {
			Class.forName("org.h2.Driver");
			String s = "jdbc:h2:./h2db/user-platform";
			// connect to h2
			conn = DriverManager.getConnection(
					s, "sa", "sa");
			stat = conn.createStatement();

			// create table
			stat.execute("drop table users");
			stat.execute("CREATE TABLE users(\n" +
					"id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
					"name VARCHAR(16) NOT NULL,\n" +
					"password VARCHAR(64) NOT NULL,\n" +
					"email VARCHAR(64) NOT NULL,\n" +
					"phoneNumber VARCHAR(32) NOT NULL)");
			stat.execute("insert into users values(1,'张三','123456','zhangsan@gmail.com','12345768911') ");
			resultSet = stat.executeQuery("select * from users");
			resultSet.next();
			System.out.println("查询出的名字是" + resultSet.getString("name") + "email:" + resultSet.getString("email"));
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				}
				catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				}
				catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (server != null) {
			System.out.println("正在关闭h2...");
			server.stop();
			System.out.println("关闭成功.");
		}
	}

}
