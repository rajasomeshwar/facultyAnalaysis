package com.spring.spring_security_learn.testing;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        String username = ((User) authentication.getPrincipal()).getUsername();
        System.out.println("User logged in: " + username);
        // You can save this information to a database or file
    }
}