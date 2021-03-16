package org.geektimes.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/17 上午12:11
 * @version 1.0
 */
public class StringConverter implements Converter<String> {

	@Override
	public String convert(String value) throws IllegalArgumentException, NullPointerException {
		return String.valueOf(value);
	}

}
