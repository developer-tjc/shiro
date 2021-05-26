package com.tang.controller;

import com.tang.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
@RequestMapping("login")
    public String login(String uname,String pass,@RequestParam(defaultValue = "false") Boolean b){

        Subject subject = SecurityUtils.getSubject();

        AuthenticationToken token = new UsernamePasswordToken(uname, pass,b);
        try{
            subject.login(token);


            return "redirect:main";
        }catch (Exception e){
            e.getMessage();
        }


        return "redirect:erro";
    }

    @RequestMapping("setone")
    @ResponseBody
    @RequiresPermissions("ms1")
    public String setone(){

        return "hello nihao !";
    }
    @RequestMapping("{uri}")
    public String getPage(@PathVariable String uri){
        System.out.println(uri+"dfsdf");
        return uri;
    }
}
