package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:32
 * @version 1.0
 */
public class DoubleConverter extends AbstractConverter<Double> {

	@Override
	protected Double doConvert(String value) {
		return Double.valueOf(value);
	}

}
