package com.example.appoitement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppoitementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppoitementApplication.class, args);
    }

}
