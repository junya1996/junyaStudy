package com.junya.spring.auth;

import com.junya.spring.repository.config.EnableMybatisPlus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMybatisPlus
public class JunyaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JunyaAuthApplication.class, args);
    }

}
