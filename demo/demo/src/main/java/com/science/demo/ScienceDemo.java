package com.science.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableJpaRepositories
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ScienceDemo {
	public static void main(String[] args) {
		SpringApplication.run(ScienceDemo.class, args);
	}
}
