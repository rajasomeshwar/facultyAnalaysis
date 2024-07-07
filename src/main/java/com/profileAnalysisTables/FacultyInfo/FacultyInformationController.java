package com.profileAnalysisTables.FacultyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profileAnalysisTables.exception.SessionExpireException;

@RestController
@RequestMapping("/T")
@CrossOrigin("*")
public class FacultyInformationController {
	
	@Autowired
	FacultyInformationService facultyInfoService;
	
	@PostMapping("/user/facultyInfo")
	public FacultyInformationT facultyInfoUpdate(@RequestBody FacultyInformationT facultyinf,Authentication auth)
	{
		if(auth==null) {
		throw new SessionExpireException("Pls Login Again !");
		
		}
		System.out.println(facultyinf);
		var v=facultyInfoService.saveInfo(facultyinf,auth.getName());
		System.out.println(auth.getName());
		
		return v;
	}
	@GetMapping("/user/facultyInfo")
	public FacultyInformationT facultyInfoGet(Authentication auth)
	{
		if(auth==null) {
		throw new SessionExpireException("Pls Login Again !");
		
		}
		var v=facultyInfoService.getInfo(auth.getName());;
		return v;
	}
//	iss: self
//	sub: rajasomeshwar143@gmail.com
//	exp: 2024-07-03T14:31:16Z
//	iat: 2024-07-03T13:01:16Z
//	jti: rajasomeshwar143@gmail.com
//	roles: USER

}
