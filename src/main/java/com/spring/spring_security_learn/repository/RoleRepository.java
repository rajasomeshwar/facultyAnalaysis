package com.spring.spring_security_learn.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.spring_security_learn.model.*;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByAuthority(String authority);
}