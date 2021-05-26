package me.young1lin.mybatis.spring.boot.starter;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/26 下午7:22
 * @version 1.0
 */
@org.springframework.context.annotation.Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties(MybatisProperties.class)

public class MyBatisAutoConfiguration implements InitializingBean {

	private final MybatisProperties properties;

	private final ResourceLoader resourceLoader;

	private final Environment environment;


	public MyBatisAutoConfiguration(MybatisProperties properties, ResourceLoader resourceLoader, Environment environment) {
		this.properties = properties;
		this.resourceLoader = resourceLoader;
		this.environment = environment;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.properties.isCheckConfigLocation() && StringUtils.hasText(this.properties.getConfigLocation())) {
			Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
			Assert.state(resource.exists(),
					"Cannot find config location: " + resource + " (please add config file or check your Mybatis configuration)");
		}
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setConfiguration(new Configuration());
		if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
			factory.setMapperLocations(this.properties.resolveMapperLocations());
		}
		if (StringUtils.hasLength(properties.getEnvironment())) {
			// 如果是 ${...} 这种类型的，让 EL 表达式替换掉
			factory.setEnvironment(environment.resolvePlaceholders(properties.getEnvironment()));
		}
		if (StringUtils.hasLength(properties.getConfigLocation())) {
			factory.setConfigLocation(resourceLoader.getResource(properties.getConfigLocation()));
		}
		if (StringUtils.hasLength(properties.getConfigurationPropertiesPath())) {
			Resource resource = resourceLoader.getResource(properties.getConfigurationPropertiesPath());
			Properties properties = new Properties();
			properties.load(resource.getInputStream());
			factory.setConfigurationProperties(properties);
		}
		factory.setFailFast(properties.isFailFast());
		if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
		}
		return factory.getObject();
	}

}
