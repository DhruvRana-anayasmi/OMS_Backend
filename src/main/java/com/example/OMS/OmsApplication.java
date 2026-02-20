package com.example.OMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OmsApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(OmsApplication.class, args);
	}
}
