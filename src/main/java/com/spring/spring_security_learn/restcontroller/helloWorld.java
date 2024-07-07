package com.spring.spring_security_learn.restcontroller;

import org.hibernate.mapping.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorld {
  @GetMapping("/helloworld")
  public String helloworld(Authentication authe)
  {
	  System.out.println("Auth "+authe.getName());
	  System.out.println("prin "+authe.getPrincipal());
	   JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authe;
       Jwt jwt = (Jwt) jwtAuthToken.getPrincipal();

       // Extract specific claims
       String username = jwt.getClaim("sub");  
       System.out.println("  nam e     "+username);
       var claims = jwt.getClaims();
       for (var entry : claims.entrySet()) {
           System.out.println(entry.getKey() + ": " + entry.getValue());
       }

	  return "hello World";
  }

}
