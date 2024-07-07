package com.profileAnalysisTables.FacultyInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_security_learn.model.ApplicationUser;

public interface FacultyInformationRepo extends JpaRepository<FacultyInformationT,Long> {

	
	FacultyInformationT findByUserAccount(ApplicationUser User);

}
