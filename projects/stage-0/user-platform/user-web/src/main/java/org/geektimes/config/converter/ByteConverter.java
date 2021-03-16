package org.geektimes.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:35
 * @version 1.0
 */
public class ByteConverter implements Converter<Byte> {

	@Override
	public Byte convert(String value) throws IllegalArgumentException, NullPointerException {
		return Byte.valueOf(value);
	}

}
