package org.geektimes.projects.user.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.geektimes.context.CustomContext;

/**
 * {@link CustomContext} 初始化器
 * ContextLoaderListener
 */
public class ComponentContextInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        CustomContext context = new CustomContext();
        context.init(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}