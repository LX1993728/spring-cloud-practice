### 使用说明
 - 官方中文文档: https://github.com/apache/skywalking/blob/v5.0.0-alpha/docs/README_ZH.md
 - 参考博客地址: https://www.jianshu.com/p/e81e35dc6406
 - 对应github地址: https://github.com/apache/skywalking/blob/master/docs/en/setup/service-agent/java-agent/README.md
 - 发行版压缩包下载地址: http://skywalking.apache.org/downloads/
 - 安装启动说明: https://www.jianshu.com/p/5524b4545421
 
### 本地调试参数
```

 -javaagent:D:\idea_springcloud_space\spring-cloud-practice\spring-cloud-skywalking\agent\skywalking-agent.jar
 -Dskywalking.collector.backend_service=localhost:11800
 -Dskywalking.agent.service_name=XXX
```