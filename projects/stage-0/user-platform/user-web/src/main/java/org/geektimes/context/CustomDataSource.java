package org.geektimes.context;

import org.h2.jdbcx.JdbcDataSource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/10 下午10:55
 * @version 1.0
 */
public class CustomDataSource extends JdbcDataSource {

	public CustomDataSource(){
		this.setURL("jdbc:h2:./h2db/user-platform");
		this.setUser("sa");
		this.setPassword("sa");
	}

}
