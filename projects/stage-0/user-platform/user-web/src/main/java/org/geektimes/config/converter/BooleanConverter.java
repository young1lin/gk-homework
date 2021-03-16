package org.geektimes.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:34
 * @version 1.0
 */
public class BooleanConverter implements Converter<Boolean> {

	@Override
	public Boolean convert(String value) throws IllegalArgumentException, NullPointerException {
		return Boolean.valueOf(value);
	}

}
