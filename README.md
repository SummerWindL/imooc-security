# imooc-security
##spring-security学习</br>
`day06 拦截器`</br>
`day07 wiremock`</br>

## 使用wiremock伪造服务
服务端：[wiremock-standalone-2.18.0.jar](http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.18.0/wiremock-standalone-2.18.0.jar)</br>
**指定端口启动服务**
$ java -jar wiremock-standalone-2.18.0.jar --port 8062</br>

客户端：
[MockServer]()

### 使用swagger2开发接口</br>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.7.0</version>
</dependency>

集成这两个工具

#### 异步处理REST服务</br>

1、使用Runnable异步处理Rest服务</br>

2、使用DeferredResult异步处理Rest服务</br>

3、异步处理配置（过滤处理）</br>

#### 伪代码</br>
  * 1、
        ``` 
        Callable<String> result = new Callable<String>() {

         @Override
         public String call() throws Exception {
           logger.info("副线程开始");
           Thread.sleep(1000);
           logger.info("副线程返回");
           return "success";
         }
        };
        ```
  * 2、
      ``` 
          String orderNumber = RandomStringUtils.randomNumeric(8);//生成八位随机数订单号
          mockQueue.setPlaceOrder(orderNumber);//模拟放入消息队列

          DeferredResult<String> result = new DeferredResult<String>();
          deferredResultHolder.getMap().put(orderNumber, result);
          ```
        

