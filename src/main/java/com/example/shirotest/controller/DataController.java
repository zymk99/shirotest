package com.example.shirotest.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {
    @RequestMapping(value="/null")
    public String ReruNull()
    {
        return null;
    }
    @RequestMapping(value="/getMenu",produces = "text/plain;charset=utf-8")
    public String getMenu()
    {
        List list=new LinkedList<Map>();
        Map m1=new HashMap();
        m1.put("id","1");
        m1.put("name","测试1");
        m1.put("icon","el-icon-document-remove");
        m1.put("url","/admin/sa-html/sa-doc.html");
        Map m2=new HashMap();
        m2.put("id","2");
        m2.put("name","测试2");
        m2.put("icon","el-icon-table-lamp");
        Map m3=new HashMap();
        m3.put("id","2-2");
        m3.put("name","测试2");
        m3.put("icon","el-icon-table-lamp");
        m3.put("url","/admin/sa-html/home/swiper.html");
        List list2=new LinkedList<Map>();
        list2.add(m3);
        m2.put("childList",list2);
        list.add(m1);
        list.add(m2);
        JSONArray json=JSONArray.fromObject(list);
        return json.toString();
    }

    @RequestMapping("/power")
    @RequiresPermissions("user:add")
    public String power()
    {
        return "123456";
    }

    @PostMapping("/login" )
    public String postLog(String name,String passwd)
    {
        Subject currentUser = SecurityUtils.getSubject();
        //注销
        currentUser.logout();
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(name, passwd);
            // 设置为rememberme
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
            }
            // 所有认证时异常的父类
            catch (AuthenticationException ae) {
                return "{}";
            }
            currentUser.hasRole("add");
        }
        return "{}";
    }
}
