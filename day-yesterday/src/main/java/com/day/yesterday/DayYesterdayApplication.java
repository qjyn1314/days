package com.day.yesterday;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

//开启nacos的动态配置文件的刷新功能
@RefreshScope
//开启nacos服务注册
@EnableDiscoveryClient
@EnableDubbo
@ComponentScan("com.day")
@EnableAutoDataSourceProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DayYesterdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DayYesterdayApplication.class, args);
    }

}
