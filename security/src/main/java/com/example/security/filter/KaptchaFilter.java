package com.example.security.filter;

import com.example.security.config.MyAuthenticationFailureHandler;
import com.example.security.exception.KaptchaException;
import com.google.code.kaptcha.Constants;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: dongjianzhu
 * @create: 2021-09-25 10:47
 **/
public class KaptchaFilter extends OncePerRequestFilter {

    private final AuthenticationFailureHandler failureHandler = new MyAuthenticationFailureHandler();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //非登录请求，直接放行
        if(!"/login".equals(httpServletRequest.getRequestURI())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else{
            try {
                verifyCaptcha(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (KaptchaException e) {
                failureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
    }

    private void verifyCaptcha(HttpServletRequest httpServletRequest) throws KaptchaException{
        String kaptcha = httpServletRequest.getParameter("kaptcha");
        System.out.println("kaptcha --> " + kaptcha);
        HttpSession session = httpServletRequest.getSession();
        String capText = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        boolean hasText = StringUtils.hasText(capText);
        if(hasText){
            //无论失败还是成功，随手清楚session中的验证码。客户端在登录失败时刷新验证码
            session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        }
        if(!hasText || !StringUtils.hasText(kaptcha) || !kaptcha.equalsIgnoreCase(capText)){
            throw new KaptchaException();
        }
    }
}
