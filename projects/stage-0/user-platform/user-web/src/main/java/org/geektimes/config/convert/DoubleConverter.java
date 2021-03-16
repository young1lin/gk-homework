package org.geektimes.config.convert;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:32
 * @version 1.0
 */
public class DoubleConverter implements Converter<Double> {

	@Override
	public Double convert(String value) throws IllegalArgumentException, NullPointerException {
		return Double.valueOf(value);
	}

}
