package com.spring.spring_security_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.spring_security_learn.userD.userOwn;

@Repository
public interface UserRepo extends JpaRepository<userOwn,Long> {


}
