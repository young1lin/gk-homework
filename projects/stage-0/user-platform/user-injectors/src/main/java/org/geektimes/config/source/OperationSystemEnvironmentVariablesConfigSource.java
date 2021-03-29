package org.geektimes.config.source;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.microprofile.config.spi.ConfigSource;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/22 上午12:49
 * @version 1.0
 */
public class OperationSystemEnvironmentVariablesConfigSource extends AbstractConfigSource {

	public OperationSystemEnvironmentVariablesConfigSource() {
		super(300, "System Environment");
	}

	@Override
	protected void setProperties() {
		super.properties = new HashMap<>(2 << 5);
		properties.putAll(System.getenv());
	}

}
