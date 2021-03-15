package com.example.shirotest;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.shirotest.mapper")
@EnableAsync
public class ShirotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirotestApplication.class, args);
    }

}
