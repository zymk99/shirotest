package com.example.shirotest.shiro;

import com.example.shirotest.dao.TUser;
import com.example.shirotest.mapper.RolePermMapper;
import com.example.shirotest.mapper.UserMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    RolePermMapper rpm;
    //执行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        System.out.println("授权");
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        TUser user = (TUser) subject.getPrincipal();

        //给资源授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(user!=null)
        {
            LinkedList<Map> role_per=rpm.getRolePerByUserid(user.getId().toString());
            if(role_per.size()!=0)
            {
                simpleAuthorizationInfo.addRole(role_per.get(0).get("rolename").toString());
                for(Map perMap:role_per)
                {
                    simpleAuthorizationInfo.addStringPermission(perMap.get("permisname").toString());
                }
            }
        }
        simpleAuthorizationInfo.addStringPermission("print:see");
        //simpleAuthorizationInfo.addStringPermission("*");
        return simpleAuthorizationInfo;
    }
    @Autowired
    UserMapper um;
    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // TODO Auto-generated method stub
        System.out.println("认证");
        //shiro判断逻辑
        UsernamePasswordToken user = (UsernamePasswordToken) arg0;
        if(user.getUsername()==null || user.getPassword()==null)
        {
            return null;
        }
        TUser realUser=um.login(new TUser(  user.getUsername(),new String(user.getPassword()) ));
        if(realUser==null)
        {
            return null;
        }
        AuthenticationInfo into=new SimpleAuthenticationInfo(realUser,user.getPassword(),getName());
        return into;
    }
}
