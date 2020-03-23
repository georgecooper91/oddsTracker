package com.trackodds.trackodds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.trackodds.trackodds.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class TrackOddsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackOddsApplication.class, args);
	}

}
