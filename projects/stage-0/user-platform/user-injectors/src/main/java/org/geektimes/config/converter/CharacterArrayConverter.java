package org.geektimes.config.converter;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/3/16 下午11:36
 * @version 1.0
 */
public class CharacterArrayConverter extends AbstractConverter<Character[]> {

	@Override
	protected Character[] doConvert(String value) {
		char[] chars = value.toCharArray();
		Character[] characters = new Character[chars.length];
		for (int i = 0; i < chars.length; i++) {
			characters[i] = chars[i];
		}
		return characters;
	}

}
