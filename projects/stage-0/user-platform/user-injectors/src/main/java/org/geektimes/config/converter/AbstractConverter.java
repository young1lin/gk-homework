package org.geektimes.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/21 下午6:37
 * @version 1.0
 */
public abstract class AbstractConverter<T> implements Converter<T> {

	@Override
	public T convert(String value) throws IllegalArgumentException, NullPointerException {
		if (value == null || "".equals(value)) {
			return null;
		}
		return doConvert(value);
	}

	/**
	 * 各个子类的模板方法
	 *
	 * @param value 属性名称
	 * @return 值
	 */
	protected abstract T doConvert(String value);

}
