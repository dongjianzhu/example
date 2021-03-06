package com.example.gome.config;

import com.example.gome.service.impl.GomeOAuth2UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 07:49
 **/
@EnableWebSecurity
//必须开启 要不然注解不能用
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    GomeOAuth2UserServiceImpl gomeOAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> {
                    authorize.anyRequest().authenticated();
                })
                .csrf().disable()
                .oauth2Login().tokenEndpoint().accessTokenResponseClient(new DefaultAuthorizationCodeTokenResponseClient())
                .and()
                .userInfoEndpoint()
                .userService(gomeOAuth2UserService);
    }


}
