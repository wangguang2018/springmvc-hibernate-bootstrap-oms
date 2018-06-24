# 娃娃机后台代码



[TOC]



## 打包命令

```gradle
frame-oms 目录下
gradle build -Denv=production
gradle build -Denv=dev
```

profile目前支持3种模式：

* *production*  生产模式
* *test*  测试模式
* *dev* 开发模式




## Tomcat启动

使用tomcat启动应用时，使用如下启动参数

```shell
-Dspring.profiles.active=development
```

profile目前支持3种模式：

- *production*  生产模式
- *test*  测试模式
- *development* 开发模式


