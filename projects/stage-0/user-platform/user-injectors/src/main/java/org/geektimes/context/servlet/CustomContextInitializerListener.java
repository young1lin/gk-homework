package org.geektimes.context.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.geektimes.config.JavaConfig;
import org.geektimes.config.servlet.ServletContextConfigInitializer;
import org.geektimes.context.core.support.AbstractCustomContext;
import org.geektimes.context.core.support.DefaultCustomContext;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/23 下午10:16
 * @version 1.0
 */
public class CustomContextInitializerListener implements ServletContextListener {

	private static final String ENVIRONMENT_NAME = "javaConfig";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 有可能取到的是空的
		JavaConfig javaConfig = (JavaConfig) sce.getServletContext()
				.getAttribute(ServletContextConfigInitializer.CONFIG_NAME);
		DefaultCustomContext context = new DefaultCustomContext();
		context.registerSingleton("javaConfig", javaConfig);
		context.init(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		AbstractCustomContext context = (AbstractCustomContext) sce.getServletContext()
				.getAttribute(AbstractCustomContext.CONTEXT_NAME);
		context.stop();
	}

}
