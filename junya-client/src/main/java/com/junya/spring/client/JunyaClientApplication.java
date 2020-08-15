package com.junya.spring.client;

import com.junya.spring.repository.config.EnableMybatisPlus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMybatisPlus
public class JunyaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JunyaClientApplication.class, args);
    }

}
