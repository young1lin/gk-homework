package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:35
 * @version 1.0
 */
public class ByteConverter extends AbstractConverter<Byte> {

	@Override
	protected Byte doConvert(String value) {
		return Byte.valueOf(value);
	}

}
