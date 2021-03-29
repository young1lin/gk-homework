package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:32
 * @version 1.0
 */
public class FloatConverter extends AbstractConverter<Float> {

	@Override
	protected Float doConvert(String value) {
		return Float.valueOf(value);
	}

}
