package org.geektimes.config.convert;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:31
 * @version 1.0
 */
public class IntegerConverter implements Converter<Integer> {

	@Override
	public Integer convert(String value) throws IllegalArgumentException, NullPointerException {
		return Integer.valueOf(value);
	}

}
