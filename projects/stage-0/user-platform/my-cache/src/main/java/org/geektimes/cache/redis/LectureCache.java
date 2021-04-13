package org.geektimes.cache.redis;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import java.io.Serializable;
import java.util.Set;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.geektimes.cache.AbstractCache;
import org.geektimes.cache.ExpirableEntry;
import org.geektimes.cache.redis.serializer.RedisSerializer;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/4/13 下午8:21
 * @version 1.0
 */
public class LectureCache<K extends Serializable, V extends Serializable> extends AbstractCache<K, V> {

	private final StatefulRedisConnection<byte[], byte[]> connection;

	private final RedisCommands<byte[], byte[]> syncCommands;

	private final RedisSerializer<? extends Serializable> redisSerializer;


	protected LectureCache(CacheManager cacheManager, String cacheName,
			Configuration<K, V> configuration, StatefulRedisConnection<byte[], byte[]> connection,
			RedisSerializer<? extends Serializable> redisSerializer) {
		super(cacheManager, cacheName, configuration);
		this.connection = connection;
		this.syncCommands = connection.sync();
		this.redisSerializer = redisSerializer;
	}

	@Override
	protected boolean containsEntry(K key) throws CacheException, ClassCastException {
		byte[] keyBytes = serialize(key);
		return syncCommands.exists(keyBytes) != 0;
	}

	@Override
	protected ExpirableEntry<K, V> getEntry(K key) throws CacheException, ClassCastException {
		byte[] keyBytes = serialize(key);
		return getEntry(keyBytes);
	}

	protected ExpirableEntry<K, V> getEntry(byte[] keyBytes) throws CacheException, ClassCastException {
		byte[] valueBytes = syncCommands.get(keyBytes);
		return ExpirableEntry.of(deserialize(keyBytes), deserialize(valueBytes));
	}

	@Override
	protected void putEntry(ExpirableEntry<K, V> entry) throws CacheException, ClassCastException {
		byte[] keyBytes = serialize(entry.getKey());
		byte[] valueBytes = serialize(entry.getValue());
		syncCommands.set(keyBytes, valueBytes);
	}

	@Override
	protected ExpirableEntry<K, V> removeEntry(K key) throws CacheException, ClassCastException {
		byte[] keyBytes = serialize(key);
		ExpirableEntry<K, V> oldEntry = getEntry(keyBytes);
		syncCommands.del(keyBytes);
		return oldEntry;
	}

	@Override
	protected void clearEntries() throws CacheException {
		// TODO 不想做
	}

	@Override
	protected Set<K> keySet() {
		// 不想做
		return null;
	}

	@Override
	protected void doClose() {
		connection.close();
	}

	protected byte[] serialize(Object obj) {
		return redisSerializer.serialize(obj);
	}

	@SuppressWarnings("unchecked")
	protected <T> T deserialize(byte[] bytes) throws CacheException {
		if (bytes == null) {
			return null;
		}
		return (T) redisSerializer.deserialize(bytes);
	}

}
