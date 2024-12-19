package com.day.gateway.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/1/1 13:12
 */
@Getter
@Setter
@Component
@RefreshScope
@ConfigurationProperties("days.gateway")
public class GateProperties {

    private String info;
    private String message;

}
