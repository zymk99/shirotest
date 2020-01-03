package com.example.shirotest.controller;

import com.example.shirotest.dao.IndexMenu;
import com.example.shirotest.mapper.IndexMenuMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    IndexMenuMapper menu;
    @RequestMapping(value="/null")
    public String ReruNull()
    {
        return null;
    }
    @RequestMapping(value="/getMenu",produces = "text/plain;charset=utf-8")
    public String getMenu()
    {
        IndexMenu[] me=menu.getMenuByAdmin();
        Map menuMap=new HashMap();
        for(IndexMenu m:me)
        {
            IndexMenu tmp_im=m;
            if(menuMap.get(m.getId())!=null)
            {
                tmp_im=(IndexMenu)menuMap.get(m.getId());
                tmp_im.setChildlist( tmp_im.getChildlist()+","+m.getChildlist() );
            }
            menuMap.put(m.getId(),tmp_im);
        }


        List list=new LinkedList<Map>();
        for(Object tmp_s:menuMap.keySet())
        {
            IndexMenu indexm=(IndexMenu)menuMap.get(tmp_s.toString());
            indexm.setChildlist("["+indexm.getChildlist()+"]");
            list.add(menuMap.get(tmp_s.toString()));
        }
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
    @PostMapping("/insertMenu")
    public String insertMenu(IndexMenu indexMenu)
    {
        indexMenu.setId(UUID.randomUUID().toString().replaceAll("-",""));
        boolean bool=menu.insertMenu(indexMenu);
        return JSONObject.fromObject("{'value':'ok'}").toString();
    }
}
