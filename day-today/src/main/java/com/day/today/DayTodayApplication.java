package com.day.today;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.day")
@SpringBootApplication
public class DayTodayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayTodayApplication.class, args);
    }

}
