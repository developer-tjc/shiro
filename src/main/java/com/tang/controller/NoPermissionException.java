package com.tang.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NoPermissionException {

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public String  handlerExcep(){
        return "没有权限";
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String handlerAuthoriz(){
        return "权限不足";
    }
}
