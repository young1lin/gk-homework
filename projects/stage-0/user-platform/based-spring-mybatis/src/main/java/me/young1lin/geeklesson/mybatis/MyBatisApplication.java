package me.young1lin.geeklesson.mybatis;

import me.young1lin.geeklesson.mybatis.annotation.EnableMyBatis;
import me.young1lin.geeklesson.mybatis.mapper.IngredientMapper;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
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
@SpringBootApplication
public class MyBatisApplication {

	public static void main(String[] args) {
		MapperScan mapperScan = AnnotatedElementUtils.getMergedAnnotation(MyBatisApplication.class, MapperScan.class);
		assert mapperScan != null;
		System.out.println(mapperScan.basePackages()[0]);
		AnnotationConfigServletWebServerApplicationContext context = (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(MyBatisApplication.class);
		IngredientMapper mapper = context.getBean(IngredientMapper.class);
		mapper.getAll().forEach(System.out::println);
	}

}
