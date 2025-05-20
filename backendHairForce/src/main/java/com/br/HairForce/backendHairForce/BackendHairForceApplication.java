package com.br.HairForce.backendHairForce;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendHairForceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		//DB
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		//JWT
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

		//MAIL
		System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
		System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));

		SpringApplication.run(BackendHairForceApplication.class, args);
	}

}
