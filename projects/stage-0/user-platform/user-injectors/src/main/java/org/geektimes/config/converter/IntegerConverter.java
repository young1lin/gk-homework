package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:31
 * @version 1.0
 */
public class IntegerConverter extends AbstractConverter<Integer> {

	@Override
	protected Integer doConvert(String value) {
		return Integer.valueOf(value);
	}

}
