package com.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.security.dao")
@SpringBootApplication
public class AuthManergerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthManergerApplication.class, args);
    }

}
