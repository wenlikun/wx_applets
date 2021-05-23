package com.benbird.inner.wxapplets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.benbird.inner.wxapplets.dao")
public class WxAppletsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxAppletsApplication.class, args);
    }

}
