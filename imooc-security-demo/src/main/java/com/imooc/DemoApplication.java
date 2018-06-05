package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//在springboot2.0中不提供这种内置tomcat方式，采用全新的tomcat配置方式。
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	
	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}
}
