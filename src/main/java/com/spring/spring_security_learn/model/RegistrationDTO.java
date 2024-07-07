package com.spring.spring_security_learn.model;

import com.profileAnalysisTables.FacultyInfo.FacultyInformationT;

public class RegistrationDTO {
    private String username;
    private String password;
    private String mobilenumber;
    
    private FacultyInformationT data;

    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String password,String mobileno,FacultyInformationT data){
        super();
        this.username = username;
        this.password = password;
        this.mobilenumber=mobileno;
        this.data=data;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getMoblienumber(){
        return this.mobilenumber;
    }

    public void setMobilenumber(String mobileno){
        this.mobilenumber = mobileno;
    }
	public FacultyInformationT getData() {
		return data;
	}

	public void setData(FacultyInformationT data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RegistrationDTO [username=" + username + ", password=" + password + ", mobilenumber= "+mobilenumber+", data=" + data + "]";
	}

   
}