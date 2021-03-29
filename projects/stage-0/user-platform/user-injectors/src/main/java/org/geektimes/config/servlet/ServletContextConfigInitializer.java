package org.geektimes.config.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.geektimes.config.JavaConfig;
import org.geektimes.config.source.ServletContextConfigSource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/23 下午9:11
 * @version 1.0
 */
public class ServletContextConfigInitializer implements ServletContextListener {

	public static final String CONFIG_NAME = ServletContextConfigInitializer.class.getName() + ":Config";


	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		ServletContextConfigSource configSource = new ServletContextConfigSource();
		configSource.setProperties(servletContext);
		JavaConfig config = new JavaConfig();
		config.addConfigSource(configSource);
		servletContextEvent.getServletContext().setAttribute(CONFIG_NAME, config);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
