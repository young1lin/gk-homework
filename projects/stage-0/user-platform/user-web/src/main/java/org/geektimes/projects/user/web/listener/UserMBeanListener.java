package org.geektimes.projects.user.web.listener;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

import org.geektimes.mbean.User;
import org.geektimes.mbean.UserMBean;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/15 下午11:47
 * @version 1.0
 */
public class UserMBeanListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("org.geektimes.mbean:type=User");
			UserMBean mbean = new User();
			mbs.registerMBean(mbean, name);
		}
		catch (Exception e) {
			// 偷个懒
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
