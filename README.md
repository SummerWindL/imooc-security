# imooc-security
##spring-security学习</br>
`day06 拦截器`</br>
`day07 wiremock伪造REST服务`</br>
`day08 自定义用户认证逻辑`</br>
`day09 个性化用户认证流程(1)`</br>

### 使用wiremock伪造服务
服务端：[wiremock-standalone-2.18.0.jar](http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.18.0/wiremock-standalone-2.18.0.jar)</br>
**指定端口启动服务**:
$ java -jar wiremock-standalone-2.18.0.jar --port 8062</br>

客户端：
[MockServer](https://github.com/SummerWindL/imooc-security/blob/master/imooc-security-demo/src/main/java/com/imooc/wiremock/MockServer.java)

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

        Callable<String> result = new Callable<String>() {

         @Override
         public String call() throws Exception {
           logger.info("副线程开始");
           Thread.sleep(1000);
           logger.info("副线程返回");
           return "success";
         }
        };
        
  * 2、
  * 
        String orderNumber = RandomStringUtils.randomNumeric(8);//生成八位随机数订单号
        mockQueue.setPlaceOrder(orderNumber);//模拟放入消息队列
        DeferredResult<String> result = new DeferredResult<String>();
        deferredResultHolder.getMap().put(orderNumber, result);


# 使用Spring Security开发基于表单的登录

## 自定义用户认证逻辑


spring security原理

* spring security过滤器链

 ![filterchina](/img/spring-security.png)


***
     处理用户信息获取逻辑        **实现UserDetailsService** 
	 
	 处理用户校验逻辑              UserDetails
 	
	 处理密码加密解密	       PasswordEncoder(使用crypto包中的)

## 个性化用户认证流程

>1.自定义登陆页面
>
![spring1](/img/spring-security1.png)

>
>2.自定义登陆成功处理
>
>3.自定义登陆失败处理


* 封装读取用户自定义properties
 
![spring2](/img/spring-security2.png)

	@ConfigurationProperties(prefix ="imooc.security")
	public class SecurityProperties {
	BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	
	}


	public class BrowserProperties {

	
	private String loginPage = "/imooc-signIn.html";

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	}

## 个性化用户认证流程（二）
>1、自定义登陆页面          http.formLogin().loginPage("/imooc-signIn.html")

>2、自定义登陆成功处理      AuthenticationSuccessHandler

>3、自定义登陆失败处理      AuthenticationFailureHandler

* spring security 默认成功跳转继承类 SavedRequestAwareAuthenticationSuccessHandler
* spring security 默认失败跳转继承类
SimpleUrlAuthenticationFailureHandler

