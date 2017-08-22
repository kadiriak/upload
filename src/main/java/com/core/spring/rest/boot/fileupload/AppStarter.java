package com.core.spring.rest.boot.fileupload;


import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = { "com.core.spring.rest.boot" })
public class AppStarter  {

	public static final String REST_SERVICE_URI = "http://localhost:8080/upload";

	public static void getFiles() {

		RestTemplate restTemplate = new RestTemplate();
		String files = restTemplate.getForObject(REST_SERVICE_URI + "/files", String.class);
		System.out.println(files);
	}

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
		
	}

	

}
