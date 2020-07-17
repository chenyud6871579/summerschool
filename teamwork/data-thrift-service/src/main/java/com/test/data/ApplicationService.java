package com.test.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class ApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationService.class);
    }
}
