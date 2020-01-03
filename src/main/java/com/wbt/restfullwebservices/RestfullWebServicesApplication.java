package com.wbt.restfullwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfullWebServicesApplication extends SpringBootServletInitializer {

    @Override
 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
  return application.sources(RestfullWebServicesApplication.class);
 }

	public static void main(String[] args) {
		SpringApplication.run(RestfullWebServicesApplication.class, args);
	}

}
