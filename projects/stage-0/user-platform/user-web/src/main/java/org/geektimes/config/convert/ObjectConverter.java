package org.geektimes.config.convert;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:41
 * @version 1.0
 */
public class ObjectConverter implements Converter<Object> {

	@Override
	public Object convert(String value) throws IllegalArgumentException, NullPointerException {
		// 什么都想转，贱不贱啊
		return value;
	}

}
