package com.example.security.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 15:02
 **/
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserServiceImpl service;

    @Test
    void loadUserByUsername() {
        UserDetails user = service.loadUserByUsername("dongjianzhu");
        System.out.println(user);
    }
}