package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dongjianzhu
 * @create: 2021-09-23 17:13
 **/
@RestController
public class HelloWorld {

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World!";
    }
}
