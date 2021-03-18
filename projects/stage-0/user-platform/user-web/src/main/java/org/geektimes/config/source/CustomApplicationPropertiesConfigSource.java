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
public class CustomApplicationPropertiesConfigSource implements ConfigSource {

	private final Map<String, String> properties;

	public CustomApplicationPropertiesConfigSource() {
		this.properties = new HashMap<>();
		Properties proTmp = null;
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
			proTmp = new Properties();
			proTmp.load(inputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> strings = proTmp.stringPropertyNames();
		Properties finalProTmp = proTmp;
		strings.forEach(key -> {
			String value = finalProTmp.getProperty(key);
			properties.put(key, value);
		});
	}


	@Override
	public Map<String, String> getProperties() {
		return properties;
	}

	@Override
	public int getOrdinal() {
		return 100;
	}

	@Override
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}

	@Override
	public String getValue(String propertyName) {
		return properties.get(propertyName);
	}

	@Override
	public String getName() {
		return "Java System Properties";
	}

}
