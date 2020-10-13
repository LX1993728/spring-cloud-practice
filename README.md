# spring-cloud-practice

### 注意事项一: 服务命名
 - spring5.x 服务调用时已不支持服务名带下划线.
 - nacos 的dataID默认是连接符是横线 '-', 如果服务名包含字符横线会影响nacos的配置导致服务不生效.
 - 最好的约定是使用大小写字母命名服务,既不包含横线也不要包含下划线.
 
### 注意事项二: swagger版本
 - swagger版本号尽量选用2.x,当前选用的版本是2.7.0.
 - swagger的3.x版本有BUG 不能使用.

### 注意事项三: JDK版本
 - RateLimiter适用的JDK版本为Java HotSpot(TM) 64-Bit Server VM **1.8.0_261**
 
### 注意事项四: endpoint
 - 为了对hystrix进行支持,需要引入actuator
 - 旧版本才有`/health`和`/info`,新版本的actuator 端点只能去`/actuator`进行访问查看, 例如`/actuator/health`和`/actuator/info`等端点
 - 旧版本有 `/hystrix.stream`, 新版本除了引入actuator依赖外,还需要在每个hystrix的应用中配置`management.endpoints.web.exposure.include=*` 才能暴露`/actuator/hystrix.stream`端点
 
TODO:// 
在zuul中集成RateLimit限流组件 
集成Hystrix以及turbine 
springbootadmin中集成javamelody 监控
