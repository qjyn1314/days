package com.day.gateway.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Knife4j 配置类
 *
 * @author wangjunming
 * @date 2022/12/20 20:41
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "knife4j.gateway")
public class Knife4jProperties {

    /**
     * 是否启用聚合Swagger组件
     */
    private boolean enable = false;

    @Resource
    private DiscoveryClient discoveryClient;

    static List<String> servers = CollUtil.newArrayList("day-today","day-yesday");

    /**
     * 构建分组数据
     *
     * @return List<Map < String, String>>
     */
    public List<Map<String, String>> build() {
        List<String> services = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(services)) {
            return new ArrayList<>();
        }
        return services.stream().filter(item -> servers.contains(item)).map(this::convert).collect(Collectors.toList());
    }

    private Map<String, String> convert(String service) {
        String url = "/" + service + "/v3/api-docs";
        String source = service + url + service;
        return MapUtil.builder("name", service)
                .put("url", url)
                .put("contextPath", service)
                .put("id", Base64.getEncoder().encodeToString(source.getBytes(StandardCharsets.UTF_8))).build();
    }

}
