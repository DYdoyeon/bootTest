package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
//@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class BootjarTestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BootjarTestApplication.class, args);

	}

}
