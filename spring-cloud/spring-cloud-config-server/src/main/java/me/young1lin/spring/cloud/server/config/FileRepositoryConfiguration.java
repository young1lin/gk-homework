package me.young1lin.spring.cloud.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/6/1 下午11:54
 * @version 1.0
 */
@Profile("file")
public class FileRepositoryConfiguration {

	@Bean
	public FileEnvironmentRepository fileEnvironmentRepository(
			ConfigurableEnvironment configurableEnvironment,
			FileEnvironmentRepositoryProperties fileEnvironmentRepositoryProperties) {
		return new FileEnvironmentRepository(configurableEnvironment,
				fileEnvironmentRepositoryProperties);
	}

}
