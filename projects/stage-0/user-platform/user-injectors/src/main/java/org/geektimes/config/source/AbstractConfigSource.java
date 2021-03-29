package org.geektimes.config.source;

import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/23 下午9:50
 * @version 1.0
 */
public abstract class AbstractConfigSource implements ConfigSource {

	private final int ordinal;

	private final String name;

	protected Map<String, String> properties;


	public AbstractConfigSource(int ordinal, String name) {
		this.ordinal = ordinal;
		this.name = name;
		setProperties();
	}

	@Override
	public int getOrdinal() {
		return ordinal;
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
		return name;
	}

	/**
	 * 设置数据信息
	 */
	protected abstract void setProperties();

}
