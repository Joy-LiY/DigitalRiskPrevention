package com.example.digitalriskprevention;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.example.digitalriskprevention.mapper")
@SpringBootApplication
public class DigitalRiskPreventionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalRiskPreventionApplication.class, args);
    }

}
