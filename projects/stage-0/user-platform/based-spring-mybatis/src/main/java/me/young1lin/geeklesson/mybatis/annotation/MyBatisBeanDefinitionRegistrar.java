/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.young1lin.geeklesson.mybatis.annotation;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

import javax.sql.DataSource;


/**
 * MyBatis 自动配置类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 */
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MyBatisBeanDefinitionRegistrar
		implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private static final Logger logger =
			LoggerFactory.getLogger(MyBatisBeanDefinitionRegistrar.class);


	private Environment environment;


	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
			BeanDefinitionRegistry registry) {
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) registry;
		// 提前依赖查找主要的数据源，提前注入
		DataSource dataSource = factory.getBean(DataSource.class);
		BeanDefinitionBuilder beanDefinitionBuilder =
				genericBeanDefinition(SqlSessionFactoryBean.class);
		Map<String, Object> attributes =
				importingClassMetadata.getAnnotationAttributes(EnableMyBatis.class.getName());
		Objects.requireNonNull(attributes, "it couldn't be happen");
		beanDefinitionBuilder.addPropertyValue("dataSource", dataSource);
		// Spring String 类型可以自动转化 Spring Resource
		beanDefinitionBuilder.addPropertyValue("configLocation",
				attributes.get("configLocation"));
		beanDefinitionBuilder.addPropertyValue("mapperLocations",
				attributes.get("mapperLocations"));
		beanDefinitionBuilder.addPropertyValue("environment",
				resolvePlaceholder(attributes.get("environment")));
		beanDefinitionBuilder.addPropertyValue("typeHandlersPackage",
				attributes.get("typeHandlersPackage"));
		beanDefinitionBuilder.addPropertyValue("typeAliasesPackage",
				attributes.get("typeAliasesPackage"));
		String configurationPropertiesPath = (String) attributes.get("configurationProperties");
		if (StringUtils.isNotEmpty(configurationPropertiesPath)) {
			Properties properties = resolveConfigurationProperties(configurationPropertiesPath);
			beanDefinitionBuilder.addPropertyValue("configurationProperties", properties);
		}
		// 自行添加其他属性
		// SqlSessionFactoryBean 的 BeanDefinition
		BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		String beanName = (String) attributes.get("value");
		registry.registerBeanDefinition(beanName, beanDefinition);
	}

	private Properties resolveConfigurationProperties(String configurationPropertiesPath) {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configurationPropertiesPath);
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		}
		catch (IOException e) {
			logger.error("can't find any properties named [{}]", configurationPropertiesPath, e);
			return null;
		}
		return properties;
	}

	private Object resolvePlaceholder(Object value) {
		if (value instanceof String) {
			return environment.resolvePlaceholders((String) value);
		}
		return value;
	}

}
