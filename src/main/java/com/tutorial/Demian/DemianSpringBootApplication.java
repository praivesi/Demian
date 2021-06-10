package com.tutorial.Demian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemianSpringBootApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(DemianSpringBootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemianSpringBootApplication.class, args);
	}

}
