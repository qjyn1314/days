package com.day.parent;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 公共的配置变量
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/27 10:26
 */
@Component
@ConfigurationProperties(prefix = "days")
public class DaysProperties {

    private static final String SERVER_PORT = "server.port";
    private static final String APPLICATION_NAME = "spring.application.name";

    @Autowired
    private Environment environment;
    private static Environment env;

    @PostConstruct
    public void init() {
        env = environment;
    }

    public static String getPort() {
        return env.getProperty(SERVER_PORT);
    }

    public static String getApplicationName() {
        return env.getProperty(APPLICATION_NAME);
    }

}
