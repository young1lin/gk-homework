package me.young1lin.spring.cloud.server.config.environment;

import java.util.Properties;

import me.young1lin.spring.cloud.server.config.resource.DynamicResourceMessageSource;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.JdbcEnvironmentRepository;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:56
 * @version 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FileEnvironmentRepository implements EnvironmentRepository {


	private final ConfigurableEnvironment configurableEnvironment;

	private final DynamicResourceMessageSource dynamicResourceMessageSource;


	public FileEnvironmentRepository(ConfigurableEnvironment configurableEnvironment,
			FileEnvironmentRepositoryProperties fileEnvironmentRepositoryProperties) {
		this.configurableEnvironment = configurableEnvironment;
		this.dynamicResourceMessageSource =
				new DynamicResourceMessageSource(fileEnvironmentRepositoryProperties.getFilePath());
	}

	/**
	 * @param application application
	 * @param profile profile
	 * @param label label
	 * @return environment
	 * @see JdbcEnvironmentRepository
	 */
	@Override
	public Environment findOne(String application, String profile, String label) {
		if (StringUtils.isEmpty(label)) {
			label = "master";
		}
		if (StringUtils.isEmpty(profile)) {
			profile = "default";
		}
		if (!profile.startsWith("default")) {
			profile = "default," + profile;
		}
		String[] profiles = new String[] {profile, "file"};
		Environment environment = new Environment(application, profiles, label, null,
				null);
		Properties properties = dynamicResourceMessageSource.getCommonMessages();
		// 想看系统属性的，可以把这个打开。
		//PropertySource systemPropertySource = new PropertySource("system",
	//			configurableEnvironment.getSystemEnvironment());
	//	environment.add(systemPropertySource);
		PropertySource propertySource = new PropertySource("custom-source", properties);
		environment.addFirst(propertySource);
		return environment;
	}

}
