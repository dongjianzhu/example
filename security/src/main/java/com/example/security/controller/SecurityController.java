package com.example.security.controller;

import com.example.security.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 07:Post**/
@Controller
public class SecurityController {

    @PostMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("name", "dongjianzhu");
        System.out.println("xxxx");
        //代码中获取 登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        System.out.println(principal);
        return "index";
    }

    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }

    @RequestMapping("/success")
    public @ResponseBody String success(){
        return "login success";
    }

//    @RequestMapping("/session/invalid")
//    public @ResponseBody String sessionInvalid(){
//        return "会话超时或无效";
//    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public @ResponseBody String admin(){
        return "拥有admin角色";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('user')")
    public @ResponseBody String user(){
        return "拥有user角色";
    }

    @RequestMapping("/adminOrUser")
    @PreAuthorize("hasAnyRole('user','admin')")
    public @ResponseBody String adminOrUser(){
        return "拥有admin或者User角色";
    }

    @RequestMapping("/remove")
    @PreAuthorize("hasRole('admin') and hasAuthority('remove')")
    public @ResponseBody String remove(){
        return "拥有admin角色,并且具有remove权限";
    }

    @RequestMapping("/update")
    @PreAuthorize("hasAuthority('update')")
    public @ResponseBody String update(){
        return "具有update权限";
    }
}
