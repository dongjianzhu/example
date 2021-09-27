package com.example.gome.controller;

import com.example.gome.entity.GomeOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 07:Post**/
@RestController
public class SecurityController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("xxx");
        return "hello";
    }

    @RequestMapping(value = {"/","/index"})
    public GomeOAuth2User index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (GomeOAuth2User) authentication.getPrincipal();
    }

}
