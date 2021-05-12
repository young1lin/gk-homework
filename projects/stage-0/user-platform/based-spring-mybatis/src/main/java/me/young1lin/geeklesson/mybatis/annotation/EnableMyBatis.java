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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 激活 MyBatis
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 * Date : 2021-05-06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@MapperScan
@Import(MyBatisBeanDefinitionRegistrar.class)
public @interface EnableMyBatis {

	/**
	 * @return the bean name of {@link SqlSessionFactoryBean}
	 */
	String value() default "sqlSessionFactoryBean";

	/**
	 * 会在启动时加载别名
	 *
	 * @return 扫描的packages
	 */
	@AliasFor(annotation = MapperScan.class)
	String[] basePackages();

	/**
	 * the location of {@link Configuration}
	 *
	 * @return
	 */
	String configLocation() default "";

	/**
	 * @return the location of {@link Mapper}
	 * @see MapperScan
	 */
	String[] mapperLocations() default {};

	String environment() default "SqlSessionFactoryBean";

	boolean failFast() default false;

	String typeHandlersPackage() default "";

	String typeAliasesPackage() default "";

	/**
	 * the location of {@link java.util.Properties}
	 *
	 * @return the location of {@link java.util.Properties} default ""
	 */
	String configurationPropertiesPath() default "";

}
