package org.geektimes.cache;

import static org.geektimes.cache.configuration.ConfigurationUtils.cacheEntryListenerConfiguration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.net.URI;

import org.geektimes.cache.event.TestCacheEntryListener;
import org.junit.Test;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/4/13 下午9:36
 * @version 1.0
 */
public class LectureTest {

	@Test
	public void testSampleRedis() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager(URI.create("redis://127.0.0.1:6379/"), null);
		// configure the cache
		MutableConfiguration<String, Integer> config =
				new MutableConfiguration<String, Integer>()
						.setTypes(String.class, Integer.class);

		// create the cache
		Cache<String, Integer> cache = cacheManager.createCache("redisCache", config);
		// add listener
		cache.registerCacheEntryListener(cacheEntryListenerConfiguration(new TestCacheEntryListener<>()));

		// cache operations
		String key = "redis-key";
		Integer value1 = 1;
		cache.put(key, value1);

		// update
		value1 = 2;
		cache.put(key, value1);

		Integer value2 = cache.get(key);
		assertEquals(value1, value2);
		cache.remove(key);
		assertNull(cache.get(key));
	}


	@Test
	public void testSampleRedis2() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager(URI.create("redis://127.0.0.1:6379/"), null);
		// configure the cache
		MutableConfiguration<String, String> config =
				new MutableConfiguration<String, String>()
						.setTypes(String.class, String.class);
		String key = "ClearLove";
		Cache<String, String> cache2 = cacheManager.createCache("redisCache2", config);
		cache2.registerCacheEntryListener(cacheEntryListenerConfiguration(new TestCacheEntryListener<>()));
		cache2.put(key, "77777");
		//cache2.remove(key);
	//	assertNull(cache2.get(key));
	}

}
