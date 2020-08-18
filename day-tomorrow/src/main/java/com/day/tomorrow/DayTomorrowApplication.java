package com.day.tomorrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DayTomorrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayTomorrowApplication.class, args);
    }

}
