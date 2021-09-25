package com.example.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 图形验证码exception
 * @author: dongjianzhu
 * @create: 2021-09-25 10:44
 **/
public class KaptchaException extends AuthenticationException {

    public KaptchaException() {
        super("图形验证码校验失败");
    }

    public KaptchaException(String msg) {
        super(msg);
    }
}
