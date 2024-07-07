package com.spring.spring_security_learn.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.spring_security_learn.model.*;


@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
	
	Optional<ApplicationUser> findByUsergmail(String username);
	
	  boolean existsByUsergmail(String username);
	Optional<ApplicationUser>  findByUserId(Long userId);
}