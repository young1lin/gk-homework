package org.geektimes.context;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @date 2021/3/16 下午11:00
 * @version 1.0
 */
public interface Context {

	/**
	 * get component object with component's name
	 *
	 * @param name component's name
	 * @see CustomContext#getComponent(String)
	 * @return component object or null
	 */
	Object getComponent(String name);

}
