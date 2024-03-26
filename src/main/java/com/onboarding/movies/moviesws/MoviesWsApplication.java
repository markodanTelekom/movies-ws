package com.onboarding.movies.moviesws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MoviesWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesWsApplication.class, args);
	}

}
