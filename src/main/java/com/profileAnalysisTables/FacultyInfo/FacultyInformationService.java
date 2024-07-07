package com.profileAnalysisTables.FacultyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.spring_security_learn.repository.UserRepository;
import com.spring.spring_security_learn.exception.UserNotFoundException;
import com.spring.spring_security_learn.model.ApplicationUser;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacultyInformationService {
	
	@Autowired
	FacultyInformationRepo facultyInformationrepo;
	@Autowired
	UserRepository userRepo;
	public FacultyInformationT saveInfo(FacultyInformationT facultydata, String usergmail)
	{
		
		
		// get the access for that 
		var userDetails=userRepo.findByUsergmail(usergmail).orElse(null);
		if(userDetails==null) {
			throw new UserNotFoundException("UserGmail not Found !");
		}
	   FacultyInformationT details=facultyInformationrepo.findByUserAccount(userDetails);
	   if(details==null) {
		   throw new UserNotFoundException("UserGmail not Found !");
	   }
	   copyDetails(details,facultydata);
	    System.out.println(details+" d "+facultydata);
	var value=facultyInformationrepo.save(details);
	return value;
	
	}
	private void copyDetails(FacultyInformationT original, FacultyInformationT duplicate) {
		// TODO Auto-generated method stub
	  original.setName(duplicate.getName());
	  original.setDepartment(duplicate.getDepartment());
	  original.setDesignation(duplicate.getDesignation());
	  original.setIndustryexperience(duplicate.getIndustryexperience());
	  original.setJoining_date(duplicate.getJoining_date());
	  original.setTeachingexperience(duplicate.getTeachingexperience());
	  long a=original.getIndustryexperience();
	   long b=original.getTeachingexperience();
	   original.setTotal_experience(a+b);
		
	}
	public FacultyInformationT getInfo(String name) {
		// TODO Auto-generated method stub
	 ApplicationUser userDetails=userRepo.findByUsergmail(name).orElse(null);
		if(userDetails==null) {
			throw new UserNotFoundException("UserGmail not Found !");
		}
	 var details=	facultyInformationrepo.findByUserAccount(userDetails);
	if(details==null)
	{
		//   write about the login fainlid 
	 return null;
	}
		return  details;
	}
	

}
