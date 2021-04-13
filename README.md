# 第六周作业

## 有关序列化抽象 API 及配置

[RedisSerializer](projects/stage-0/user-platform/my-cache/src/main/java/org/geektimes/cache/redis/serializer/RedisSerializer.java)

[DefaultRedisSerializer](projects/stage-0/user-platform/my-cache/src/main/java/org/geektimes/cache/redis/serializer/DefaultRedisSerializer.java)

[StringRedisSerializer](projects/stage-0/user-platform/my-cache/src/main/java/org/geektimes/cache/redis/serializer/StringRedisSerializer.java) 【这里有个坑，实现非常简单，不能存除 String 类型外的内容】

[default-caching-provider.properties](projects/stage-0/user-platform/my-cache/src/main/resources/META-INF/default-caching-provider.properties)

> 注：按照 Kafka 那样玩，应该是拆成两个接口的，不过这里偷懒了，遵循简单原则。

## 有关 CacheManger 相关的类

[LectureTest](projects/stage-0/user-platform/my-cache/src/test/java/org/geektimes/cache/LectureTest.java)

[LectureCache](projects/stage-0/user-platform/my-cache/src/main/java/org/geektimes/cache/redis/LectureCache.java)

[LectureManager](projects/stage-0/user-platform/my-cache/src/main/java/org/geektimes/cache/redis/LectureCacheManager.java)

