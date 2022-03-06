package com.robertmyjak.notification;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(
        scanBasePackages = {
                "com.robertmyjak.notification",
                "com.robertmyjak.aqmp"
        }
)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.robertmyjak.clients")
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
