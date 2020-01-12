package com.example.shirotest.controller;

import com.example.shirotest.dao.IndexMenu;
import com.example.shirotest.dao.TUser;
import com.example.shirotest.mapper.IndexMenuMapper;
import com.example.shirotest.mapper.UserMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    IndexMenuMapper menu;
    @Autowired
    UserMapper um;
    @RequestMapping(value="/null")
    public String ReruNull()
    {
        return null;
    }
    //获取首页
    @RequestMapping("/getHomePage")
    public String getHomePage()
    {
        Subject sub=SecurityUtils.getSubject();
        if(sub.hasRole("admin"))
        {
            return "/admin/my-html/main.html";
        }
        return "/admin/my-html/main2.html";
    }
    //获取菜单
    @RequestMapping(value="/getMenu",produces = "text/plain;charset=utf-8")
    public String getMenu()
    {
//        Subject sj=SecurityUtils.getSubject();
//        sj.isPermitted("print");
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
    //删除菜单节点
    @PostMapping(value="/deleItem")
    @RequiresPermissions("menuindex:delete")
    public String deleItemById(@RequestBody Map<String,String> par)
    {
        String val="";
        if(menu.deleItemById(par.get("id")))
        {
            val="{'flag':true}";
        }
        else
        {
            val="{'flag':false}";
        }
        return val;
    }
    //新增菜单节点
    @PostMapping("/insertMenu")
    @RequiresPermissions("menuindex:add")
    public String insertMenu(IndexMenu indexMenu)
    {
        indexMenu.setId(UUID.randomUUID().toString().replaceAll("-",""));
        if(menu.insertMenu(indexMenu))
        {
            return JSONObject.fromObject("{'flag':true}").toString();
        }
        return JSONObject.fromObject("{'flag':false}").toString();
    }

    //修改菜单节点
    @PostMapping("/updateMenu")
    @RequiresPermissions("menuindex:update")
    public String updateMenu(IndexMenu indexMenu)
    {
        if(indexMenu!=null && indexMenu.getId()!=null && menu.updateItemById(indexMenu))
        {
            return JSONObject.fromObject("{'flag':true}").toString();
        }
        return JSONObject.fromObject("{'flag':false}").toString();
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

    @PostMapping(value="/getAllUser" , produces = "text/plain;charset=utf-8")
    public String getAllUser(@RequestBody Map<String,String> map)
    {
        if(StringUtils.isBlank(map.get("pagenum")) ||StringUtils.isBlank(map.get("pagesize")) )
        {
            return null;
        }
        int beginindex=(Integer.parseInt(map.get("pagenum"))-1)*Integer.parseInt(map.get("pagesize"));
        map.put("beginindex", String.valueOf( beginindex )  );
        List<TUser> userList= um.selectAllUser(map);
        if(userList.size()>0)
        {
            for(TUser tmp:userList)
            {
                tmp.setIndex(++beginindex);
            }
        }
//        if(userList.size()<Integer.parseInt(map.get("pagesize")))
//        {
//            for(int i=userList.size();i<Integer.parseInt(map.get("pagesize"));i++)
//            {
//                userList.add(new TUser());
//            }
//        }
        return (JSONArray.fromObject(userList) ).toString();
    }
}



