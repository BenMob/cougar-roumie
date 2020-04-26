package com.SE370.Cougar.Roomie.controller.errorhandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.SE370.Cougar.Roomie.errorhandling")

public class ErrorHandlingApplication {
    public static void main(String [] args) {
        System.setProperty("spring.profiles.active", "errorhandling");
        SpringApplication.run(ErrorHandlingApplication.class, args);
    }
}
