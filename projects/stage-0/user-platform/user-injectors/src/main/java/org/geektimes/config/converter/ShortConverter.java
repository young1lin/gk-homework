package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:41
 * @version 1.0
 */
public class ShortConverter extends AbstractConverter<Short> {

	@Override
	protected Short doConvert(String value) {
		return Short.valueOf(value);
	}

}
