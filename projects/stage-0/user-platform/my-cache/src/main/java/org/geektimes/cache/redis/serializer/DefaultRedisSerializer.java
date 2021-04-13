package org.geektimes.cache.redis.serializer;

import javax.cache.CacheException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * DefaultRedisSerializer with {@link ByteArrayInputStream} & {@link ObjectInputStream}
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/4/13 下午8:47
 * @version 1.0
 */
public class DefaultRedisSerializer<T> implements RedisSerializer<T> {

	public DefaultRedisSerializer() {
	}

	@Override
	public byte[] serialize(Object obj) {
		byte[] bytes;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		     ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
		) {
			// Key -> byte[]
			objectOutputStream.writeObject(obj);
			bytes = outputStream.toByteArray();
		}
		catch (IOException e) {
			throw new CacheException(e);
		}
		return bytes;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T deserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		T value;
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		     ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
		) {
			value = (T) objectInputStream.readObject();
		}
		catch (Exception e) {
			throw new CacheException(e);
		}
		return value;
	}

}
