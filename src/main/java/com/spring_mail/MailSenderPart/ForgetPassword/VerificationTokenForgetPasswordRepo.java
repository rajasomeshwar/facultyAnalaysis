package com.spring_mail.MailSenderPart.ForgetPassword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.spring_security_learn.model.ApplicationUser;

@Repository
public interface VerificationTokenForgetPasswordRepo extends JpaRepository<VerficationTokenForgetPassword,Long> {
	VerficationTokenForgetPassword findByToken(String token);
	VerficationTokenForgetPassword findByUser(ApplicationUser user);
}
