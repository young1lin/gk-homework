package me.young1lin.spring.cloud.server.config.environment;

import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:54
 * @version 1.0
 */
@Configuration
public class FileRepositoryConfiguration {

	@Bean
	public EnvironmentRepository fileEnvironmentRepository(
			ConfigurableEnvironment configurableEnvironment,
			FileEnvironmentRepositoryProperties fileEnvironmentRepositoryProperties) {
		return new FileEnvironmentRepository(configurableEnvironment,
				fileEnvironmentRepositoryProperties);
	}

}
