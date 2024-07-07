package com.profileAnalysisTables.FacultyInfo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.spring_security_learn.model.ApplicationUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FacultyInformationT {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private LocalDate joining_date;
	private String department;
	private String designation;
	private long teachingexperience;
	private long industryexperience;
	private long total_experience;
	
	
	@JsonIgnore
	@OneToOne(mappedBy = "facultyInformation")
    private ApplicationUser userAccount;
	
	
	public FacultyInformationT()
	{
		super();
////		this.id = id;
		
		this.name = "";
		this.joining_date = LocalDate.now();
		this.department = "";
		this.designation = "";
		this.teachingexperience = 0L;
		this.industryexperience =0L;
		this.total_experience = 0;
	}
	public FacultyInformationT(long id, String name, LocalDate joining_date, String department, String designation,
			long teachingexperience, long industryexperience, long total_experience,ApplicationUser user) {
		super();
		this.id = id;
		this.name = name;
		this.joining_date = joining_date;
		this.department = department;
		this.designation = designation;
		this.teachingexperience = teachingexperience;
		this.industryexperience = industryexperience;
		this.total_experience = total_experience;
		this.userAccount=user;
	}
	
	
	
	
	
	
	public ApplicationUser getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(ApplicationUser userdetails) {
		this.userAccount = userdetails;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getJoining_date() {
		return joining_date;
	}
	public void setJoining_date(LocalDate joining_date) {
		this.joining_date = joining_date;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public long getTeachingexperience() {
		return teachingexperience;
	}
	public void setTeachingexperience(long teachingexperience) {
		this.teachingexperience = teachingexperience;
	}
	public long getIndustryexperience() {
		return industryexperience;
	}
	public void setIndustryexperience(long industryexperience) {
		this.industryexperience = industryexperience;
	}
	public long getTotal_experience() {
		return total_experience;
	}
	public void setTotal_experience(long total_experience) {
		this.total_experience = total_experience;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "FacultyInformationT [id=" + id + ", name=" + name + ", joining_date=" + joining_date + ", department="
				+ department + ", designation=" + designation + ", teachingexperience=" + teachingexperience
				+ ", industryexperience=" + industryexperience + ", total_experience=" + total_experience
				+ ", userAccount=" + "]";
	}
	
	
	
	
	
	
	
	
	

}
