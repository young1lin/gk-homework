# 第七周作业

项目在[这](oauth2/oauth2-simple)

# 第十周作业

在[这](projects/stage-0/user-platform/based-spring-mybatis)

# 第十一周作业

在[这](projects/stage-0/user-platform/consistent-hash)

# 第十二周作业

在[这](projects/stage-0/user-platform/based-spring-mybatis)，启动名字为 `Week12MyBatisApplication` 类即可，启动前需要 `mvn install` 一下 `mybatis-spring-boot-starter` 这个模块

# 第十三周作业

在[这](spring-cloud/spring-cloud-config-server/src/main/java/me/young1lin/spring/cloud/server/config/ConfigServerApplication.java)，启动该类即可。

在 http://localhost:8888/young1lin/file 这里访问。

更改 `META-INF/config/default.properties` 配置就可以发生变化。再次访问上面的地址，就能发现配置变了（Mac OS 采用轮询机制来搞的，所以会有一点点延迟，改了不一定立马变化）。
