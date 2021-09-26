package com.example.gome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GomeApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GomeApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
