package com.example.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dongjianzhu
 */
@SpringBootApplication
@MapperScan("com.example.security.mapper")
public class SecurityApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SecurityApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
