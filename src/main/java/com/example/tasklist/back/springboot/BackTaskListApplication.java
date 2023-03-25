package com.example.tasklist.back.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BackTaskListApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackTaskListApplication.class, args);
	}

}
