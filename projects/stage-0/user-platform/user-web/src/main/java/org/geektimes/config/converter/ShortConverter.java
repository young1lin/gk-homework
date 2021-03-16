package org.geektimes.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:41
 * @version 1.0
 */
public class ShortConverter implements Converter<Short> {

	@Override
	public Short convert(String value) throws IllegalArgumentException, NullPointerException {
		// 用自带的方法兜底
		return Short.valueOf(value);
	}

}