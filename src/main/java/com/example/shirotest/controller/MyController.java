package com.example.shirotest.controller;

import com.example.shirotest.dao.TUser;
import com.example.shirotest.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class MyController {
    @Autowired
    UserMapper um;
    @RequestMapping(value = "/one",method = RequestMethod.GET)
    public String one()
    {
        TUser tu=um.selectU("02601a4e6ae949f3b7bb93a2b2d1b80d");
        return "html/index";
    }
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login()
    {
        return "admin/login";
    }

    @RequestMapping("/success")
    public String succe()
    {
        return "admin/index";
    }
}
