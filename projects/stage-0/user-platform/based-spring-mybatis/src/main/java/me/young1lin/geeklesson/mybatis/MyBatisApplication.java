package me.young1lin.geeklesson.mybatis;

import me.young1lin.geeklesson.mybatis.annotation.EnableMyBatis;
import me.young1lin.geeklesson.mybatis.mapper.IngredientMapper;
import me.young1lin.mybatis.spring.boot.starter.MyBatisAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/12 下午7:53
 * @version 1.0
 */
@EnableMyBatis(
		basePackages = "me.young1lin.geeklesson.mybatis.mapper",
		mapperLocations = {"classpath*:mappers/**/*.xml"},
		environment = "development")
// 需要测试另一个时，把注释去掉即可，并且把另一个 SpringBootApplication 注解注释掉
//@SpringBootApplication
public class MyBatisApplication {

	public static void main(String[] args) {
		// 只是展示 @AliasFor 的一种用法
		MapperScan mapperScan = AnnotatedElementUtils.getMergedAnnotation(MyBatisApplication.class, MapperScan.class);
		assert mapperScan != null;
		System.out.println(mapperScan.basePackages()[0]);
		new SpringApplicationBuilder(MyBatisAutoConfiguration.class)
				.web(WebApplicationType.NONE)
				.run()
				.getBean(IngredientMapper.class)
				.listAll()
				.forEach(System.out::println);
	}

}