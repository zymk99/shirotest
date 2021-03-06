package com.example.shirotest.shiro;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilter.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 设置login URL
        shiroFilter.setLoginUrl("/test/login");
        // 登录成功后要跳转的链接
        shiroFilter.setSuccessUrl("/test/success");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        //静态资源的处理
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
        filterChainDefinitionMap.put("/test/one", "anon");
        filterChainDefinitionMap.put("/sa-resources/**", "anon");
        filterChainDefinitionMap.put("/admin/**", "anon");
        filterChainDefinitionMap.put("/data/login", "anon");
        filterChainDefinitionMap.put("/data/downImg", "anon");
        filterChainDefinitionMap.put("/admin/my-html/buildzip.js", "anon");
        filterChainDefinitionMap.put("/data/createWord", "anon");
        filterChainDefinitionMap.put("/filepic/getall", "anon");

        // 退出系统的过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "anon");

        filterChainDefinitionMap.put("/kaptcha", "anon");

        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }
    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
//        return new AdminWebSessionManager();
    }
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(UserRealm real, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(real);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    //创建Realm
    @Bean(name="userRealm")
    public UserRealm getUserRealm(){
        UserRealm ur=new UserRealm();
        //设置缓存权限
        ur.setAuthorizationCachingEnabled(false);
        return ur;
    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
