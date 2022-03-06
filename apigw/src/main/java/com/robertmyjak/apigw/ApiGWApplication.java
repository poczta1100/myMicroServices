package com.robertmyjak.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiGWApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGWApplication.class, args);
    }

}
