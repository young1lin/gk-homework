package org.geektimes.projects.user.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.List;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.sql.DBConnectionManager;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/1 下午8:39
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

	private UserRepository repository;

	public UserServiceImpl() {
		String s = "jdbc:h2:./h2db/user-platform";
		try {
			Connection connection = DriverManager.getConnection(s, "sa", "sa");
			this.repository = new DatabaseUserRepository(new DBConnectionManager(connection));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean register(User user) {
		User existUser = repository.getByName(user.getName());
		if (existUser != null) {
			throw new IllegalArgumentException("已有相同名字注册用户，请核对改改名字");
		}
		return repository.save(user);
	}

	@Override
	public boolean deRegister(User user) {
		return false;
	}

	@Override
	public boolean update(User user) {
		return false;
	}

	@Override
	public User queryUserById(Long id) {
		return null;
	}

	@Override
	public User queryUserByNameAndPassword(String name, String password) {
		return null;
	}

	@Override
	public Collection<User> getAll() {
		return repository.getAll();
	}
}
