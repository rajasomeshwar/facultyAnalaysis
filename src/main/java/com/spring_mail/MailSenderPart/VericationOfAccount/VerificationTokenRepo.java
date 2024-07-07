package com.spring_mail.MailSenderPart.VericationOfAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.spring_security_learn.model.ApplicationUser;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerficationToken,Long> {
      VerficationToken findByToken(String token);
      VerficationToken findByUser(ApplicationUser user);
}
