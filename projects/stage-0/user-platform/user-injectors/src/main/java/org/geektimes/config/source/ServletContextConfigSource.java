package org.geektimes.config.source;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/23 下午9:13
 * @version 1.0
 */
public class ServletContextConfigSource extends AbstractConfigSource {

	private ServletContext servletContext;

	public ServletContextConfigSource() {
		super(400, "Servlet Environment Properties");
	}

	@Override
	protected void setProperties() {

	}

	public void setProperties(ServletContext servletContext){
		super.properties = new HashMap<>(2 << 5);
		Enumeration<String> parameterNames = servletContext.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			properties.put(parameterName, servletContext.getInitParameter(parameterName));
		}
	}

}
