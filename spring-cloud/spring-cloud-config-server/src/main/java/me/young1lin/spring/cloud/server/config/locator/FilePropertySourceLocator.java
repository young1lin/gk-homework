package me.young1lin.spring.cloud.server.config.locator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import me.young1lin.spring.cloud.server.config.resource.DynamicResourceMessageSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * 以我这个为最高优先级
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午10:59
 * @version 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FilePropertySourceLocator implements PropertySourceLocator, InitializingBean {

	private String filePath;

	private DynamicResourceMessageSource dynamicResourceMessageSource;

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public PropertySource<?> locate(Environment environment) {
		DynamicResourceMessageSource dynamicResourceMessageSource =
				new DynamicResourceMessageSource(filePath);
		Properties properties = dynamicResourceMessageSource.getCommonMessages();
		assert properties != null : "properties 不能为空";
		Map<String, Object> map =
				getMapFromProperties(properties);
		return new MapPropertySource("young1lin-config", map);
	}

	private Map<String, Object> getMapFromProperties(Properties commonMessages) {
		Map<String, Object> result = new HashMap<>(commonMessages.size());
		commonMessages.forEach((k, v) -> result.put(k.toString(), v));
		return result;
	}

}
