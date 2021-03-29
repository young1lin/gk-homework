package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/17 上午12:11
 * @version 1.0
 */
public class StringConverter extends AbstractConverter<String>{


	@Override
	protected String doConvert(String value) {
		return String.valueOf(value);
	}
}
