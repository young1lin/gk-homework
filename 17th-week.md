# 第一周到第十六周的总结

1. J2EE 规范——Servlet、JMS、JSP 简单了解了不借助 Spring 系框架如何开发 Java Web 项目（这个我毕业设计就没用 Spring 写的，就是这种项目）。
   1. 其实四年前就写过了，而且写得不少，小马哥拓宽了我的视野，不止于开发，更多的是文档的理解。
2. Java Logging 方面的内容，以及 JMX 的内容
   1. Java Logging 让我了解不止是 Apache Logging 和 Slf4j 的内容，原来 Java 官方曾经也想统一规范日志的内容。
   2. JMX 让我知道了什么是 MBean，什么是 Jolokia，如何通过远程的方式修改 Java 程序内部的内容。
3. 再次介绍了 J2EE 相关的规范，以及 Java Caching 相关的内容，还有 Java Security 的内容。
   1. 简单介绍了如何用动态代理来实现 Java Caching。
   2. 还有介绍了 Java Security 的方方面面。工作中其实也有用到，更改对应的配置文件。
4. 介绍了 JDBC、以及 Spring MVC 相关的内容。
   1. JDBC 其实我很熟，我写的毕设项目就是纯用 JDBC + JSP + Servlet 实现的。当然，MyBatis 的内容我也比较熟悉，看过了相关的书籍《MyBatis 源码深度解析》。
   2. Spring MVC 的源码简单介绍，这个其实我在对应的书籍上已经看过了，也 Debug 过源码，所以没有难度，只是复习一下。
5. 再次介绍了 MBean 的使用，以及测试方面的内容。
   1. 我记得有 MicroProfile 相关的介绍，是环境配置方面的，增长了见识，感谢小马哥。
6. 介绍了 Spring Session 以及 Spring AOP 相关的内容，重构了缓存相关的内容。
7. 简单介绍了 Dubbo 以及 Dubbo 的 SPI 相关内容。
8. 详细介绍了 Dubbo Admin、 Dubbo LoadBalance 相关的内容，以及 Nacos 相关的内容。
   1. 感谢小马哥增长了这部分的见识。
9. 介绍了 Spring Security、Netflix Hystrix、Alibaba  Sentinel 内容。
   1. 增长了 Spring Security 这部分的内容。
10. 使用 Spring Boot Starter 机制，重构了 MyBatis 项目。
    1. 作业是 Spring Boot MyBatis，根据注解写出 @EnableMyBatis 的内容。再次练习了 Spring Boot Starter 这块内容。
11. 介绍了 Spring Boot Actuator、Spring Boot Testing、代码覆盖率的内容、Spring Cloud Config 的内容。
    1. 作业是写 Spring Cloud Config 自定义的 file 协议。我是照着其他的协议，看着源码，写的，不是很难，更深一步了解了 Spring 相关内容。
12. 介绍了 Spring Security OAuth 2 相关内容、Spring Cloud Circuit Breaker 内容。
    1. 作业是 Spring Security OAuth2 的使用，按照官方的文档写了出来。
13. 介绍 Docker 、 Spring Batch、GraalVM
    1. 这个 Docker 的内容，大致的介绍了一遍，以及对应的 Dockerfile 的书写。
    2. Spring Batch 是 Cloud Native 相关的内容，在 Josh Long 的书《Cloud Native Java》里面其实有介绍，我也写过对应的笔记。当时对这个不是特别了解，小马哥讲了之后，更了解了，感谢小马哥的教导。
    3. 作业好像是使用 GraalVM 打包一个 Spring Boot 程序，跑起来。
14. K8s 的介绍以及 GraalVM 的使用。
    1. 打出了对应的包，以及作业书写，加深了对 GraalVM 的理解。作业是重构
    2. K8s 的内容的讲解，以及官方教程的使用。
    3. 作业是将上周的作业，打包成 Docker 容器。加深了对 Docker 以及 GraalVM 的理解。

**感谢小马哥的这十七周的教学，感谢班班，也感谢助教，也谢谢训练营的小伙伴的一起坚持，共同进步**。

山水有相逢，下次再会。

**谢谢**。
