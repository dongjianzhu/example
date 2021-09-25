package com.example.security.config;

import com.example.security.filter.KaptchaFilter;
import com.example.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 07:49
 **/
@EnableWebSecurity
//必须开启 要不然注解不能用
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //所有请求需要认证
        http.authorizeRequests(requests -> requests.anyRequest().authenticated())
                //启用跨域请求
                .cors()
                .and()
            //可以放行URL.antMatcher("")
            .formLogin().loginPage("/login.html").permitAll().loginProcessingUrl("/login")
            .successHandler(new MyAuthenticationSuccessHandler()).failureHandler(new MyAuthenticationFailureHandler())
            .and().exceptionHandling().and().csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                //针对非GET，需要前台传X-CSRF-TOKEN
//                .ignoringAntMatchers("/login")
                //禁用csrf防御
                .disable()
                .rememberMe().userDetailsService(userDetailsService)
            //配置一个固定的key 以防多实例web服务 remember-me失效
            .key("uuid").and().sessionManagement().maximumSessions(1);
                //默认是false  达到最大会话数量 而不是 踢掉旧的会话 而是阻止新的会话
//                .maxSessionsPreventsLogin(true);
        //将kaptchaFilter放在UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(new KaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        //放行验证码
        web.ignoring().antMatchers("/kaptcha.jpg");
    }

    /**
     * 跨域配置
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        //允许从百度站点跨域
        configuration.setAllowedOrigins(Collections.singletonList("https://www.baidu.com"));
        //允许GET POST 请求
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        //允许带凭证（一般指cookie）
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //对所有URL 有效
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * 加密算法 自带加盐
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    /**
     *  认证成功的handler
     */
    public static class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println("{\"exceptionId\":\"null\",\"messageCode\":\"200\"," +
                    "\"message\": \"Login successfully.\",\"serverTime\": " + System.currentTimeMillis() +"}");
        }
    }

}
