package com.day.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/1/1 13:12
 */
@Data
@Configuration
@ConfigurationProperties("calm.gateway")
public class GateProperties {

    private String info;
    private String message;

}
