package org.geektimes.context;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/10 下午9:40
 * @version 1.0
 */
public class BeanDefinition {

	private String name;

	private String className;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "BeanDefinition{" +
				"name='" + name + '\'' +
				", className='" + className + '\'' +
				'}';
	}

}
