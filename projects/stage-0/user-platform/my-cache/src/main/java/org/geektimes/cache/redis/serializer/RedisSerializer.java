package org.geektimes.cache.redis.serializer;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @date 2021/4/13 下午8:27
 * @version 1.0
 */
public interface RedisSerializer<T> {

	/**
	 * serialize object to byte array
	 *
	 * @param obj be serialized object
	 * @return obj's byte array
	 */
	byte[] serialize(Object obj);

	/**
	 * deserialize byte array to object
	 *
	 * @param bytes Byte array to be deserialized
	 * @return object
	 */
	T deserialize(byte[] bytes);

}
