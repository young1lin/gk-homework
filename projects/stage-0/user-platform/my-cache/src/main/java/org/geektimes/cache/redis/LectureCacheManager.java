package org.geektimes.cache.redis;

import javax.cache.Cache;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;

import java.io.Serializable;
import java.net.URI;
import java.util.Properties;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import org.geektimes.cache.AbstractCacheManager;
import org.geektimes.cache.redis.serializer.RedisSerializer;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/4/13 下午8:19
 * @version 1.0
 */
public class LectureCacheManager extends AbstractCacheManager {

	private final StatefulRedisConnection<byte[], byte[]> connection;

	private final RedisSerializer<? extends Serializable> redisSerializer;


	@SuppressWarnings("unchecked")
	public LectureCacheManager(CachingProvider cachingProvider, URI uri,
			ClassLoader classLoader, Properties properties) {
		super(cachingProvider, uri, classLoader, properties);
		RedisClient redisClient = RedisClient.create(uri.toString());
		connection = redisClient.connect(new ByteArrayCodec());
		String serializerClassName = (String) properties.get("redis.serializer");
		try {
			Class<?> clazz = Class.forName(serializerClassName);
			redisSerializer = (RedisSerializer<? extends Serializable>) clazz.newInstance();
		}
		catch (Exception e) {
			// 偷个懒
			throw new IllegalStateException("Redis Serializer Class [" + serializerClassName + "] is illegal", e);
		}
	}

	@Override
	protected <K, V, C extends Configuration<K, V>> Cache doCreateCache(String cacheName, C configuration) {
		return new LectureCache(this, cacheName, configuration, connection, redisSerializer);
	}

	@Override
	protected void doClose() {
		connection.close();
	}

}
