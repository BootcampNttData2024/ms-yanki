package com.vasquez.msyanki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsYankiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsYankiApplication.class, args);
	}

}
