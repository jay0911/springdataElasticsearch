package com.techprimers.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
public class SpringElasticDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringElasticDemoApplication.class, args);
	}
}
