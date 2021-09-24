package com.example.security.controller;

import com.example.security.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 07:48
 **/
@Controller
public class SecurityController {

    @RequestMapping({"/", "/index"})
    public String index(Model model){
        model.addAttribute("name", "dongjianzhu");
        System.out.println("xxxx");
        //代码中获取 登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        System.out.println(principal);
        return "index";
    }

    @RequestMapping("/success")
    @ResponseBody
    public String success(){
        return "login success";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public String admin(){
        return "拥有admin角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('user')")
    public String user(){
        return "拥有user角色";
    }

    @RequestMapping("/adminOrUserb ")
    @ResponseBody
    @PreAuthorize("hasAnyRole('user','admin')")
    public String adminOrUser(){
        return "拥有admin或者User角色";
    }

}
