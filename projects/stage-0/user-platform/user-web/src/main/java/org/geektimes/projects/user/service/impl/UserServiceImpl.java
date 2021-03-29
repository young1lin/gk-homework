package org.geektimes.projects.user.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;

import org.geektimes.context.core.support.DefaultCustomContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/1 下午8:39
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

	private UserRepository repository;

	@Resource(name = "validator")
	private Validator validator;


	public UserServiceImpl() {
	}

	@PostConstruct
	public void init() {
		this.repository = (DatabaseUserRepository) DefaultCustomContext.getInstance().getComponent("databaseUserRepository");
	}

	@Override
	public boolean register(User user) {
		User existUser = repository.getByName(user.getName());
		if (existUser != null) {
			throw new IllegalArgumentException("已有相同名字注册用户，请核对改改名字");
		}
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		if (violations.size() == 0) {
			return repository.save(user);
		}
		StringBuilder s = new StringBuilder();
		violations.forEach(c -> s.append(c.getMessage()));
		throw new IllegalArgumentException(s.toString());
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
