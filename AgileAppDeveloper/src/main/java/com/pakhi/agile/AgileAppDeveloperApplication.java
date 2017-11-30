package com.pakhi.agile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pakhi.agile.beans.AgileUser;
import com.pakhi.agile.constants.ROLES;
import com.pakhi.agile.jparepository.AgileUserRepository;

@SpringBootApplication
public class AgileAppDeveloperApplication {

	private static final Logger log = LoggerFactory.getLogger(AgileAppDeveloperApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AgileAppDeveloperApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(AgileUserRepository agileUserRepository) {
		return(evt) -> {
			AgileUser result = agileUserRepository.save(new AgileUser("santra.sanchita13@gmail.com", ROLES.DEVELOPER, "Sanchita", "Santra"));
			log.info(result.toString());
		};
	}
}
