package org.geektimes.projects.user.repository;

import org.geektimes.context.CustomContext;
import org.geektimes.function.ThrowableFunction;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang.ClassUtils.wrapperToPrimitive;

import javax.annotation.PostConstruct;

public class DatabaseUserRepository implements UserRepository {

	private static Logger logger = Logger.getLogger(DatabaseUserRepository.class.getName());

	private static String s = "jdbc:h2:./h2db/user-platform";

	/**
	 * 通用处理方式
	 */
	private static Consumer<Throwable> COMMON_EXCEPTION_HANDLER = e -> logger.log(Level.SEVERE, e.getMessage());

	public static final String INSERT_USER_DML_SQL =
			"INSERT INTO users(name,password,email,phoneNumber) VALUES(?,?,?,?)";

	public static final String QUERY_ALL_USERS_DML_SQL = "SELECT id,name,password,email,phoneNumber FROM users";

	private DBConnectionManager dbConnectionManager;

	public DatabaseUserRepository() {
	}

	@PostConstruct
	public void init() {
		this.dbConnectionManager = (DBConnectionManager) CustomContext.getInstance().getComponent("dbConnectionManager");

	}

	public DatabaseUserRepository(DBConnectionManager dbConnectionManager) {
		this.dbConnectionManager = dbConnectionManager;
	}

	private Connection getConnection() {
		return dbConnectionManager.getConnection();
	}

	@Override
	public boolean save(User user) {
		Connection connection = dbConnectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		boolean isSuccess = false;
		try {
			preparedStatement = connection.prepareStatement(INSERT_USER_DML_SQL);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhoneNumber());
			isSuccess = preparedStatement.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				}
				catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			//dbConnectionManager.releaseConnection();
		}
		return isSuccess;
	}

	@Override
	public boolean deleteById(Long userId) {
		return false;
	}

	@Override
	public boolean update(User user) {
		return false;
	}

	@Override
	public User getById(Long userId) {
		return executeQuery("select * from users where id = ?", resultSet -> {
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
			}
			return user;
		}, COMMON_EXCEPTION_HANDLER, userId);
	}

	@Override
	public User getByName(String username) {
		return executeQuery("select * from users where name = ?", resultSet -> {
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
			}
			return user;
		}, COMMON_EXCEPTION_HANDLER, username);
	}

	@Override
	public User getByNameAndPassword(String userName, String password) {
		return executeQuery("SELECT id,name,password,email,phoneNumber FROM users WHERE name=? and password=?",
				resultSet -> {
					// TODO
					return new User();
				}, COMMON_EXCEPTION_HANDLER, userName, password);
	}

	@Override
	public Collection<User> getAll() {
		return executeQuery("SELECT id,name,password,email,phoneNumber FROM users", resultSet -> {
			// BeanInfo -> IntrospectionException
			BeanInfo userBeanInfo = Introspector.getBeanInfo(User.class, Object.class);
			List<User> users = new ArrayList<>();
			while (resultSet.next()) { // 如果存在并且游标滚动 // SQLException
				User user = new User();
				for (PropertyDescriptor propertyDescriptor : userBeanInfo.getPropertyDescriptors()) {
					String fieldName = propertyDescriptor.getName();
					Class fieldType = propertyDescriptor.getPropertyType();
					String methodName = resultSetMethodMappings.get(fieldType);
					// 可能存在映射关系（不过此处是相等的）
					String columnLabel = mapColumnLabel(fieldName);
					Method resultSetMethod = ResultSet.class.getMethod(methodName, String.class);
					// 通过放射调用 getXXX(String) 方法
					Object resultValue = resultSetMethod.invoke(resultSet, columnLabel);
					// 获取 User 类 Setter方法
					// PropertyDescriptor ReadMethod 等于 Getter 方法
					// PropertyDescriptor WriteMethod 等于 Setter 方法
					Method setterMethodFromUser = propertyDescriptor.getWriteMethod();
					// 以 id 为例，  user.setId(resultSet.getLong("id"));
					setterMethodFromUser.invoke(user, resultValue);
				}
				users.add(user);
			}
			return users;
		}, e -> {
			// 异常处理
		});
	}

	/**
	 * @param sql
	 * @param function
	 * @param <T>
	 * @return
	 */
	protected <T> T executeQuery(String sql, ThrowableFunction<ResultSet, T> function,
			Consumer<Throwable> exceptionHandler, Object... args) {
		Connection connection = getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				Class<?> argType = arg.getClass();

				Class<?> wrapperType = wrapperToPrimitive(argType);

				if (wrapperType == null) {
					wrapperType = argType;
				}

				// Boolean -> boolean
				String methodName = preparedStatementMethodMappings.get(argType);
				Method method = PreparedStatement.class.getMethod(methodName, wrapperType);
				method.invoke(preparedStatement, i + 1, args);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			// 返回一个 POJO List -> ResultSet -> POJO List
			// ResultSet -> T
			return function.apply(resultSet);
		}
		catch (Throwable e) {
			exceptionHandler.accept(e);
		}
		return null;
	}


	private static String mapColumnLabel(String fieldName) {
		return fieldName;
	}

	/**
	 * 数据类型与 ResultSet 方法名映射
	 */
	static Map<Class<?>, String> resultSetMethodMappings = new HashMap<>();

	static Map<Class<?>, String> preparedStatementMethodMappings = new HashMap<>();

	static {
		resultSetMethodMappings.put(Long.class, "getLong");
		resultSetMethodMappings.put(String.class, "getString");
		preparedStatementMethodMappings.put(Long.class, "setLong");
		preparedStatementMethodMappings.put(String.class, "setString");
	}
}
