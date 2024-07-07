package com.spring.spring_security_learn.model;

import java.util.ArrayList;
import java.util.Collection;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.profileAnalysisTables.ContributToDepartment.ContributionToDepartmentTable;
import com.profileAnalysisTables.FacultyInfo.FacultyInformationT;
import com.profileAnalysisTables.contributionToSchool.ContributionToSchoolTable;
import com.profileAnalysisTables.contributionToSociety.ContributionToSocietyTable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usersGmail")
public class ApplicationUser implements UserDetails{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
	@Column(unique=true)
    private String usergmail;
    private String password;
    private boolean isEnabled=false;
     private String mobilenumber;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_info_id", referencedColumnName = "id")
    private FacultyInformationT facultyInformation;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ContributionToSchoolTable> contributionsToSchool; // Added this line
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ContributionToDepartmentTable> contributionToDepartmentTable;
    



	public ApplicationUser(Integer userId, String usergmail, String password,String mobileNo, boolean isEnabled, Set<Role> authorities,
			FacultyInformationT facultyInformation) {
		super();
		this.userId = userId;
		this.usergmail = usergmail;
		this.password = password;
		this.mobilenumber=mobileNo;
		this.isEnabled = isEnabled;
		this.authorities = authorities;
		this.facultyInformation = facultyInformation;
		this.contributionsToSchool=new ArrayList<>();
		this.contributionToDepartmentTable=new ArrayList<>();
	}
	public ApplicationUser() {
		super();
		authorities = new HashSet<>();
		facultyInformation=new FacultyInformationT();
		this.contributionsToSchool=new ArrayList<>();
		this.contributionToDepartmentTable=new ArrayList<>();
		
	}
	
	public List<ContributionToSchoolTable> getContributionsToSchool() {
		return contributionsToSchool;
	}
	
	
	
	public void setContributionsToSchool(List<ContributionToSchoolTable> contributionsToSchool) {
		this.contributionsToSchool = contributionsToSchool;
	}



	public List<ContributionToDepartmentTable> getContributionToDepartmentTable() {
		return contributionToDepartmentTable;
	}



	public void setContributionToDepartmentTable(List<ContributionToDepartmentTable> contributionToDepartmentTable) {
		this.contributionToDepartmentTable = contributionToDepartmentTable;
	}




	
  
	 
    public FacultyInformationT getFacultyInformation() {
		return facultyInformation;
	}


	public void setFacultyInformation(FacultyInformationT facultyInformation) {
		this.facultyInformation = facultyInformation;
	}


	public String getUsergmail() {
		return usergmail;
	}


	public void setUsergmail(String usergmail) {
		this.usergmail = usergmail;
	}


	

    public String getMobilenumber() {
		return mobilenumber;
	}



	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}



	public Integer getUserId() {
		return this.userId;
	}
	
	public void setId(Integer userId) {
		this.userId = userId;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public boolean getEnabled() {
	 return	this.isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usergmail;
	}
	
	public void setUsername(String username) {
		this.usergmail = username;
	}
	
	/* If you want account locking capabilities create variables and ways to set them for the methods below */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}



	@Override
	public String toString() {
		return "ApplicationUser [userId=" + userId + ", usergmail=" + usergmail + ", password=" + password
				+ ", isEnabled=" + isEnabled + ", mobilenumber=" + mobilenumber + ", authorities=" + authorities
				+ ", facultyInformation=" + facultyInformation + "]";
	}


	
    
}