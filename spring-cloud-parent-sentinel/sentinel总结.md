# spring-sentinel-practice

##SpringCloud 整合 alibaba微服务依赖
 - 根据springboot与springcloud版本关系对照 [博客地址](http://www.soolco.com/post/78369_1_1.html) 
 根据映照表数据地址 ` https://start.spring.io/actuator/info ` 当前的springboot版本为2.2.0.RELEASE版本,
 对应的spring-cloud版本为Hoxton.SR9版本 且对应的spring-cloud-alibaba的版本为2.2.1.RELEASE版本.
 - 实际上以nacos作为注册中心和配置中心暂未发现什么问题, 但是使用sentinel依赖的服务都返回xml类型的数据. 解决方案就
 是将sentinel相关的版本提升到最新版即2.2.3.RELEASE版本即可解决不返回json的问题.
 - 注意在配置类中定义RestTemplate的Bean方法时必须添加 `@LoadBalanced` 注解, 否则无法根据服务名去命中访问地址导致
 异常.经调试最终版本确定为 springboot2.2.0.RELEASE+ Hoxton.SR9 + spring-cloud-alibaba[2.2.3.RELEASE]
 

 