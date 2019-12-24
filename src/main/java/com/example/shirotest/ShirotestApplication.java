package com.example.shirotest;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.example.shirotest.mapper")
public class ShirotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirotestApplication.class, args);
    }

}
