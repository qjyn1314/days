package com.day.gateway.controller;

import com.day.gateway.config.GateProperties;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation("请求测试动态的配置信息接口")
    @GetMapping("/getGateInfo")
    public String getNowDate() {
        final ServerHttpRequest request = (ServerHttpRequest) RequestContextHolder.currentRequestAttributes();
        return gateProperties.toString();
    }

}
