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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

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
                //可以放行URL
                //.antMatcher("")
            .formLogin().loginPage("/login.html").permitAll().loginProcessingUrl("/login")
            .successHandler((request, response, authentication) -> {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println("{\"exceptionId\":\"null\",\"messageCode\":\"200\"," +
                        "\"message\": \"Login successfully.\",\"serverTime\": " + System.currentTimeMillis() +"}");
            }).failureHandler((request, response, exception) -> {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println("{\"exceptionId\":\"null\",\"messageCode\":\"401\"," +
                        "\"message\": \""+ exception.getMessage() +"\",\"serverTime\": " + System.currentTimeMillis() +"}");
            }).and().exceptionHandling().and().csrf().disable().sessionManagement().maximumSessions(1);
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
        web.ignoring().antMatchers("/kaptcha");
    }

    /**
     * 加密算法 自带加盐
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        //$2a$10$r8G5IAYKzNbVRs4mCGCAMuWp8aSY17NipAQHUGMcLaNnaz9oSVOkS
        //$2a$10$IrknGL2lt17yDtYbKeNIweR1jiBe33ap2pAyrZ.ehNwyuSS3YfVMG
        //$2a$10$hYdHHuayUV7xIcqR3F6Rre/h2TTOgUI122hrwcqYXUhPNcP03Ucdq
        String encode = new BCryptPasswordEncoder().encode("1234");
        System.out.println(encode);

        boolean flag = new BCryptPasswordEncoder().matches("1235",
                "$2a$10$r8G5IAYKzNbVRs4mCGCAMuWp8aSY17NipAQHUGMcLaNnaz9oSVOkS");
        System.out.println(flag);
    }

}
