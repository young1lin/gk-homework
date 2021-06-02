package me.young1lin.spring.cloud.server.config;

import java.util.Properties;

import me.young1lin.spring.cloud.server.config.resource.DynamicResourceMessageSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:56
 * @version 1.0
 */
public class FileEnvironmentRepository implements EnvironmentRepository, InitializingBean {

	private final FileEnvironmentRepositoryProperties fileEnvironmentRepositoryProperties;

	private final ConfigurableEnvironment configurableEnvironment;

	private DynamicResourceMessageSource dynamicResourceMessageSource;

	public FileEnvironmentRepository(ConfigurableEnvironment configurableEnvironment,
			FileEnvironmentRepositoryProperties fileEnvironmentRepositoryProperties) {
		this.configurableEnvironment = configurableEnvironment;
		this.fileEnvironmentRepositoryProperties = fileEnvironmentRepositoryProperties;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.dynamicResourceMessageSource =
				new DynamicResourceMessageSource(fileEnvironmentRepositoryProperties.getFilePath());
	}


	@Override
	public Environment findOne(String application, String profile, String label) {
		Environment environment = new Environment("fileEnvironment", "file");

		//PropertySource propertySource = new PropertySource();
		//environment.add();
		return null;
	}

}
