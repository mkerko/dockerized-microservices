package com.test.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.test.store")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
