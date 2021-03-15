package org.geektimes.mbean;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/15 下午11:40
 * @version 1.0
 */
public interface UserMBean {

	/**
	 * user's name
	 * @return user name
	 */
	String getName();

	/**
	 * user's age
	 * @return user age
	 */
	int getAge();

	/**
	 * set user age
	 *
	 * @param age user age
	 */
	void setAge(int age);

	/**
	 * user's email
	 * @return email address
	 */
	String getEmail();

	/**
	 * set email address
	 * @param email address about email
	 */
	void setEmail(String email);

}
