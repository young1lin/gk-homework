package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:34
 * @version 1.0
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

	@Override
	protected Boolean doConvert(String value) {
		return Boolean.valueOf(value);
	}

}
