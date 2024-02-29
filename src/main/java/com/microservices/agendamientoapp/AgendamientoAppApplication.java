package com.microservices.agendamientoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AgendamientoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamientoAppApplication.class, args);
	}

}
