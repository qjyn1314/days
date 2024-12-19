package com.day.gateway.controller;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.day.gateway.config.GateProperties;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/1/1 13:12
 */
@RestController
public class GateWayController {

    @Autowired
    private GateProperties gateProperties;

    /**
     * 此处将测试本地配置文件属性读取与nacos配置文件中的属性读取的先后顺序是什么?
     *
     */
    @Operation(method = "请求测试动态的配置信息接口")
    @GetMapping("/getGateInfo")
    public String getNowDate() {
        return gateProperties.toString();
    }

}
