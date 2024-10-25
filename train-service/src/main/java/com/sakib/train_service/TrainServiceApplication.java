package com.sakib.train_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainServiceApplication.class, args);
	}

}
