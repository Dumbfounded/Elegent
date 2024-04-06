package com.jkoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.jkoss")
@ImportResource({"classpath:spring/spring-shiro.xml"})
@PropertySource({ "classpath:jdbc.properties", "classpath:druid.properties" })
public class Application {

	public static void main(String[] args) {
		//spring boot 启动
		SpringApplication.run(Application.class, args);

	}

}
