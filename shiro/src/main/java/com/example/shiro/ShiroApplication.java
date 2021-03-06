package com.example.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShiroApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ShiroApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
