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

# 第十五周作业

在[这](spring-native-demo-project/target1/spring-native-demo-project)

## Mac OS下

两种方式启动

方式一

```shell
./spring-native-demo-project
```

方式二

双击该文件即可

## Windows 下

```shell
mvn -Pnative package
```

在 project 下执行该命令即可，打包需要很久。

# 第十六周作业

我已经按照官方文档，在 Ubuntu 环境下，将项目打包了，所以这里执行的是 Ubuntu 下可执行的文件。

Dockerfile 里面写的就是 `FROM ubuntu18.04`

在这个[文件夹下](/spring-native-demo-project-ubuntu/target1)，开启 dos 窗口，依次执行下面命令即可（记得打开 Docker）。

```shell
docker build -t young1lin/native-project .

docker run -d --name native-project -p 8080:8080 young1lin/native-project
```
