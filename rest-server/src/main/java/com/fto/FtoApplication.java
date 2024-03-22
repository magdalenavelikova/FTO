package com.fto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtoApplication.class, args);
	}

}
