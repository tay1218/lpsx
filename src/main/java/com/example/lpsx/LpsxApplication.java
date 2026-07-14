package com.example.lpsx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 联朋升学 — 启动类
 */
@SpringBootApplication
@MapperScan("com.example.lpsx.mapper")
public class LpsxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LpsxApplication.class, args);
    }
}
