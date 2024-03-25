package com.trevally_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.trevally_challenge.domain.entities")
public class TrevallyChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrevallyChallengeApplication.class, args);
	}

}
