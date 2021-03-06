# imooc-security
##spring-security学习</br>
`day06 拦截器`</br>
`day07 wiremock伪造REST服务`</br>
`day08 自定义用户认证逻辑`</br>
`day09 个性化用户认证流程(1)`</br>
`day10 个性化用户认证流程(2)`</br>
`day11 个性化定制图形验证码`</br>
`day12 记住我配置及原理`</br>
`day13 短信验证码发送`

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

## 个性化定制图形验证码基于配置方式（略）

## 记住我配置及原理

![spring1](/img/remember-me.png)

![spring1](/img/remember-me2.png)

> ### 配置
>1、页面添加记住我checkbox，name必须是remember-me
>
	<tr>
		<td colspan="2"><input name ="remember-me" type="checkbox" value="true"/>记住我</td>
	</tr>

>效果
<tr>
				<td colspan="2"><input name ="remember-me" type="checkbox" value="true"/>记住我</td>
			</tr>

>2、BrowserProperties.java新增过期时间
>private int rememberSeconds = 3600;</br>

BrowserSecurityConfig新增 persistentTokenRepository方法</br>

```	/**
* 记住我配置
* @return
*/
@Bean
public PersistentTokenRepository persistentTokenRepository() {
JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
tokenRepository.setDataSource(dataSource);
tokenRepository.setCreateTableOnStartup(true);//启动时系统自动建立这张表
return tokenRepository;
}
```

需要引入<br>
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
>3、BrowserSecurityConfig的configure方法配置登陆动作<br>
``	.and()
	.rememberMe()
		.tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(securityProperties.getBrowser().getRememberSeconds())
		.userDetailsService(userDetailsService)
``

## 短信验证码发送（层级架构）
![spring1](/img/recode.png)