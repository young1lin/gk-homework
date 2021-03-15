package org.geektimes.mbean;


import javax.management.MXBean;

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


	public User() {
		// 之后重构把这些内容，通过配置文件注入进来
		this.name = "Yasir";
		this.age = 12;
		this.email = "young1lin0108@gmail.com";
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

}
