package com.day.today;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo
@ComponentScan("com.day")
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DayTodayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayTodayApplication.class, args);
    }

}
