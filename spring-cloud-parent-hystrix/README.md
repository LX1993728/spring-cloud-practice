# spring-sentinel-practice

### 注意事项-1: 服务命名
 - spring5.x 服务调用时已不支持服务名带下划线.
 - nacos 的dataID默认是连接符是横线 '-', 如果服务名包含字符横线会影响nacos的配置导致服务不生效.
 - 最好的约定是使用大小写字母命名服务,既不包含横线也不要包含下划线.
 
### 注意事项-2: swagger版本
 - swagger版本号尽量选用2.x,当前选用的版本是2.7.0.
 - swagger的3.x版本有BUG 不能使用.

### 注意事项-3: JDK版本
 - RateLimiter适用的JDK版本为Java HotSpot(TM) 64-Bit Server VM **1.8.0_261**
 
### 注意事项-4: endpoint
 - 为了对hystrix进行支持,需要引入actuator
 - 旧版本才有`/health`和`/info`,新版本的actuator 端点只能去`/actuator`进行访问查看, 例如`/actuator/health`和`/actuator/info`等端点
 - 旧版本有 `/hystrix.stream`, 新版本除了引入actuator依赖外,还需要在每个hystrix的应用中配置`management.endpoints.web.exposure.include=*` 才能暴露`/actuator/hystrix.stream`端点

### 注意事项-5: zuul静态资源路由失败
 - zuul转发到对应微服务下的静态资源会出现问题,是因为页面中的ajax请求大部分为 `src='/xxx' ` 这会直接路由到zuul根目录下导致无法加载静态资源. 解决方案如下:
 - 首先配置微服务的上下文路径和它在zuul网关中路由前缀相同并在zuul网关中配置不不剥离前缀,因为带静态资源的微服务配置了context-path所以也要配置服务发现元数据的context-path 否则无法暴露endpoint信息. 配置如下:
 - 以微服务HystrixDashboard为例, 
  ```
    server.servlet.context-path=/${spring.application.name} 
    # 配置context-path后 需要在服务发现的元数据中配置contextPath属性
    spring.sentinel.nacos.discovery.metadata.management.context-path=${server.servlet.context-path}/actuator
  ```
 - 然后再zuul网关服务中做如下配置
 ```
    zuul.routes.HystrixDashboard.strip-prefix=false
    zuul.routes.HystrixDashboard.path=/HystrixDashboard/**
 ```
 - 另外需要注意的是zuul是不支持转发长连接请求的

### 待解决问题
 - zuul全局回退失效问题 
TODO:// 
服务应用中集成sleuth
springbootadmin中集成javamelody监控 hsytrix trubine swagger-ui logtash
