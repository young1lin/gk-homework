package org.geektimes.context.core;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @date 2021/3/29 下午9:40
 * @version 1.0
 */
public interface SingletonRegistry {

	/**
	 * Register the given existing object as singleton in the bean registry,
	 * under the given bean name
	 *
	 * @param beanName the name of the bean
	 * @param singletonObject the existing singleton object
	 */
	void registerSingleton(String beanName, Object singletonObject);

	/**
	 * Return the (raw) singleton object registered under the given name
	 *
	 * @param beanName the name of the bean to look for
	 * @return the registered singleton object, or {@code null} if none found
	 */
	Object getSingleton(String beanName);

	/**
	 * Check if this registry contains a singleton instance with the given name
	 *
	 * @param beanName the name of the bean to look for
	 * @return if this bean factory contains a singleton instance with the given name
	 */
	boolean containsSingleton(String beanName);

	/**
	 * Return the names of singleton beans registered in this registry
	 * @return the list of names as a String array (never {@code null})
	 */
	String[] getSingletonNames();

	/**
	 * Return the number of singleton beans registered in this registry
	 *
	 * @return the number of singleton beans
	 */
	int getSingletonCount();

	/**
	 * 不谈了，这个不想弄，返回个 null
	 *
	 * @return {@code null}
	 */
	default Object getSingletonMutex() {
		return null;
	}

}
