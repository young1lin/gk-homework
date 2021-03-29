package org.geektimes.config.source;

import java.util.HashMap;

/**
 * Java 系统变量
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/15 下午11:32
 * @version 1.0
 */
public class JavaSystemPropertiesConfigSource extends AbstractConfigSource {

	public JavaSystemPropertiesConfigSource() {
		super(200, "Java System Properties");

	}

	@Override
	protected void setProperties() {
		// 2 << 5 aka 32
		super.properties = new HashMap<>(2 << 5);
		for (String propertyName : System.getProperties().stringPropertyNames()) {
			this.properties.put(propertyName, System.getProperties().getProperty(propertyName));
		}
	}

}
