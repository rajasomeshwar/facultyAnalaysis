package com;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.spring_security_learn.model.ApplicationUser;
import com.spring.spring_security_learn.model.Role;
import com.spring.spring_security_learn.repository.RoleRepository;
import com.spring.spring_security_learn.repository.UserRepository;

@SpringBootApplication
@EnableMethodSecurity
public class SpringSecurityLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLearnApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}
}
