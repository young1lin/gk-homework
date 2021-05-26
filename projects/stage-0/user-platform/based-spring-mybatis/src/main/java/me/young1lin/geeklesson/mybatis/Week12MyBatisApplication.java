package me.young1lin.geeklesson.mybatis;

import me.young1lin.geeklesson.mybatis.mapper.IngredientMapper;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 第十二周作业，如果要查看之前的，需要按照 MyBatisApplication 的注释来
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/26 下午8:53
 * @version 1.0
 */
@MapperScan(value = "me.young1lin.geeklesson.mybatis.mapper")
@SpringBootApplication
public class Week12MyBatisApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Week12MyBatisApplication.class)
				.web(WebApplicationType.NONE)
				.run()
				.getBean(IngredientMapper.class)
				.listAll()
				.forEach(System.out::println);
	}

}
