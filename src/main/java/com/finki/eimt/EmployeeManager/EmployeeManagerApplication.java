package com.finki.eimt.EmployeeManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmployeeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagerApplication.class, args);
    }

}
