package com.epam.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReviewProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewProductServiceApplication.class, args);
		System.out.println("review service running.......");
	}

}
