package com.day.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DayGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayGatewayApplication.class, args);
    }

}
