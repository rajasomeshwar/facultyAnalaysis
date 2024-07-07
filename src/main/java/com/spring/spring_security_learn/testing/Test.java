package com.spring.spring_security_learn.testing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/hi")
    public String resVl()
    {
    	return "Hi";
    }
}
