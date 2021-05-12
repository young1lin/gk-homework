package me.young1lin.geeklesson.mybatis.congfiguration;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/12 下午10:11
 * @version 1.0
 */
@Configuration
public class H2Configuration {

	@Bean
	public DataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:./h2db/user-platform");
		dataSource.setUser("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

}
