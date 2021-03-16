package org.geektimes.config;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Set;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.geektimes.config.converter.*;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @author mercyblitz
 */
public class JavaConfig implements Config {

	/**
	 * 内部可变的集合，不要直接暴露在外面
	 */
	private final List<ConfigSource> configSources = new LinkedList<>();

	private Map<Class<?>, Converter<?>> converterMap;


	public JavaConfig() {
		ClassLoader classLoader = getClass().getClassLoader();
		ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load(ConfigSource.class, classLoader);
		serviceLoader.forEach(configSources::add);
		// 排序
		Comparator<ConfigSource> configSourceComparator = (o1, o2) -> (
				Integer.compare(o2.getOrdinal(), o1.getOrdinal()));
		configSources.sort(configSourceComparator);
		initialConvertersMap();
	}

	private void initialConvertersMap() {
		converterMap = new HashMap<>(16);
		converterMap.put(Boolean.class, new BooleanConverter());
		converterMap.put(Byte.class, new ByteConverter());
		converterMap.put(Character[].class, new CharacterArrayConverter());
		converterMap.put(Double.class, new DoubleConverter());
		converterMap.put(Float.class, new FloatConverter());
		converterMap.put(Integer.class, new IntegerConverter());
		converterMap.put(Long.class, new LongConverter());
		converterMap.put(Short.class, new ShortConverter());
		converterMap.put(String.class,new StringConverter());
		converterMap.put(Object.class, new ObjectConverter());
	}

	@Override
	public <T> T getValue(String propertyName, Class<T> propertyType) {
		String propertyValue = getPropertyValue(propertyName);
		Optional<Converter<T>> converterOptional = getConverter(propertyType);
		if (!converterOptional.isPresent()) {
			throw new UnsupportedOperationException("can not find this " + propertyType + " type converter");
		}
		return converterOptional.get().convert(propertyValue);
	}

	@Override
	public ConfigValue getConfigValue(String propertyName) {
		// 不想写
		return null;
	}

	@Override
	public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
		T value = getValue(propertyName, propertyType);
		return Optional.ofNullable(value);
	}

	@Override
	public Iterable<String> getPropertyNames() {
		Set<String> propertyNames = new HashSet<>(configSources.size() << 2);
		configSources.forEach(e -> propertyNames.addAll(e.getPropertyNames()));
		return propertyNames;
	}

	@Override
	public Iterable<ConfigSource> getConfigSources() {
		return Collections.unmodifiableList(configSources);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
		return Optional.ofNullable((Converter<T>) converterMap.get(forType));
	}

	@Override
	@Deprecated
	public <T> T unwrap(Class<T> type) {
		// 用不到
		return null;
	}

	protected String getPropertyValue(String propertyName) {
		String propertyValue = null;
		for (ConfigSource configSource : configSources) {
			propertyValue = configSource.getValue(propertyName);
			if (propertyValue != null) {
				break;
			}
		}
		return propertyValue;
	}

}
