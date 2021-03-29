package org.geektimes.config.source;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/15 下午11:32
 * @version 1.0
 */
public class CustomApplicationPropertiesConfigSource extends AbstractConfigSource {

	public CustomApplicationPropertiesConfigSource() {
		super(100, "Default Resource Properties");

	}

	@Override
	protected void setProperties() {
		super.properties = new HashMap<>(2 << 5);
		Properties proTmp = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
			if (inputStream != null) {
				proTmp.load(inputStream);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> strings = proTmp.stringPropertyNames();
		strings.forEach(key -> {
			String value = proTmp.getProperty(key);
			properties.put(key, value);
		});
	}

}
