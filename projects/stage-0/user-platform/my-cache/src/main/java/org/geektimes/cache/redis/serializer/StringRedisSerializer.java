package org.geektimes.cache.redis.serializer;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/4/13 下午9:47
 * @version 1.0
 */
public class StringRedisSerializer implements RedisSerializer<String> {

	public StringRedisSerializer() {
	}

	@Override
	public byte[] serialize(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString().getBytes();
	}

	@Override
	public String deserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		return String.valueOf(bytes);
	}

}
