package com.munoz.sisgestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InfracionesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfracionesServiceApplication.class, args);
	}

}
