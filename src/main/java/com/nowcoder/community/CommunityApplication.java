package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
自动扫描当前包及其子包中的所有组件，包括但不限于Controller、Service、Repository等。
启用Spring Boot的自动配置功能，根据项目的依赖自动配置Spring应用程序。
将该类标识为Spring的配置类，这在功能上等同于XML配置文件中的标签
*/

public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
