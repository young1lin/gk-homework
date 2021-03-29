package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:40
 * @version 1.0
 */
public class LongConverter extends AbstractConverter<Long> {

	@Override
	protected Long doConvert(String value) {
		return Long.valueOf(value);
	}
}
