package org.geektimes.projects.user.jmx;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.management.MXBean;

import org.eclipse.microprofile.config.Config;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/15 下午11:42
 * @version 1.0
 */
@MXBean
public class User implements UserMBean {

	private String name;

	private int age;

	private String email;

	@Resource(name = "javaConfig")
	Config config;

	public User() {
	}

	@PostConstruct
	public void init() {
		this.name = config.getValue("user.name", String.class);
		this.age = config.getValue("user.age", Integer.class);
		this.email = config.getValue("user.email", String.class);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", config=" + config +
				'}';
	}
	
}
