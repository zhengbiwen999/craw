# craw

#### 网关调用

http://10.88.1.20:8010/demo/hello?accesToken=afaf 不拦截，return hellp word
http://10.88.1.20:8010/demo/hello                 拦截

#### 服务调用
http://10.88.1.20:8081/hello        return hello word

## 读取配置 dev 环境
http://localhost:6002/master/application-dev.yml