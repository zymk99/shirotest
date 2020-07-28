package com.example.shirotest.section;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


//@Aspect
//@Controller
public class AOPTest {
    Logger log= LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(public * com.example.shirotest.controller.DataController.*(..))")
    public void allFunction(){};

    @Before("allFunction()")
    public void printFunName(JoinPoint joinPoint){
        log.debug("------"+joinPoint.getSignature().getName());
    }
}
