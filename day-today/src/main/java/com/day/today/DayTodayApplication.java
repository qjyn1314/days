package com.day.today;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.day.parent")
@EnableEurekaClient
@SpringBootApplication
public class DayTodayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayTodayApplication.class, args);
    }

}
