package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(Playerrepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Player( "Bauer"));
			repository.save(new Player("Brian"));
			repository.save(new Player("Bauer"));
			repository.save(new Player( "Palmer"));
			repository.save(new Player( "Dessler"));
		};
	}
}