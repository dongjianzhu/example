package com.example.gome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.gome.mapper")
public class GomeApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GomeApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
