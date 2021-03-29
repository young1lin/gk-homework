package org.geektimes.config;

import java.util.Optional;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @date 2021/3/21 下午6:53
 * @version 1.0
 */
public interface Environment {

	/**
	 * 按照指定类型和名称获取对应的值
	 *
	 * @param propertyName 配置属性名称
	 * @param propertyType 配置属性类型的 Class
	 * @param <T> 配置属性类型
	 * @return 对应属性的 value
	 */
	<T> T getValue(String propertyName, Class<T> propertyType);

	/**
	 * 获取 Optional 的值
	 *
	 * @param propertyName 配置属性名称
	 * @param propertyType 配置属性类型的 Class
	 * @param <T> 配置属性类型
	 * @return 对应属性的 Optional(value)
	 */
	<T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType);

	/**
	 * 获得所有属性的名称
	 *
	 * @return 所有属性的名称
	 */
	Iterable<String> getPropertyNames();

	/**
	 * 获得所有的配置源信息
	 *
	 * @return 所有配置源信息
	 */
	Iterable<ConfigSource> getConfigSources();

	/**
	 * 获得 Optional 的 Converter 类型
	 *
	 * @param forType 对应的 Converter 的 Class
	 * @param <T> Converter 类型
	 * @return Optional 的 Converter 类型
	 */
	<T> Optional<Converter<T>> getConverter(Class<T> forType);

	/**
	 * 获取对应的属性的 String 类型的值
	 *
	 * @param propertyName 属性名称
	 * @return 属性的 String 类型的值
	 */
	String getValue(String propertyName);

	/**
	 * 添加配置源
	 *
	 * @param configSource 配置源
	 */
	void addConfigSource(ConfigSource configSource);

}
