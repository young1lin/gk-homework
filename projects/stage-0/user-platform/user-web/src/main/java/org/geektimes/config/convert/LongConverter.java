package org.geektimes.config.convert;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:40
 * @version 1.0
 */
public class LongConverter implements Converter<Long> {

	@Override
	public Long convert(String value) throws IllegalArgumentException, NullPointerException {
		return Long.valueOf(value);
	}

}
