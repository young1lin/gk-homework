package org.geektimes.projects.user.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

import org.geektimes.context.Context;
import org.geektimes.context.ContextRunner;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:07
 * @version 1.0
 */
public class RegisterUserMBeanContextRunner implements ContextRunner {

	@Override
	public void run(Context context) {
		UserMBean user = (UserMBean) context.getComponent("userMBean");
		System.out.println("current user attributes are : " + user);
		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("org.geektimes.mbean:type=User");
			mbs.registerMBean(user, name);
		}
		catch (Exception e) {
			// 这里代码格式化是 Spring 的格式化的风格
			// 偷个懒
			e.printStackTrace();
		}
	}

}
