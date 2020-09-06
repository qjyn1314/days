package com.day.parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 * explain: 公共的配置变量
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/27 10:26
 */
@Component
@DependsOn("springContextHolder")
@ConfigurationProperties(prefix = "days")
public class DaysProperties {

    private static final String SERVER_PORT = "server.port";

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

}
