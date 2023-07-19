package com.kasina.automobileapi;

import com.kasina.automobileapi.model.Role;
import com.kasina.automobileapi.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class AutomobileapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomobileapiApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			Set<Role> roles = Set.of(
					new Role("ROLE_ADMIN"),
					new Role("ROLE_SELLER"),
					new Role("ROLE_USER")
			);

			roleRepository.saveAll(roles);

		};
	}*/

}
