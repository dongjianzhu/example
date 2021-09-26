package com.example.gome.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 07:Post**/
@RestController
public class SecurityController {

    @RequestMapping("/hello")
    public String hello(Authentication authentication){
        return authentication.getName();
    }
}
