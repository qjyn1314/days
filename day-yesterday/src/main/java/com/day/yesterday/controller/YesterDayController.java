package com.day.yesterday.controller;

import com.day.api.config.DubboNacosGroup;
import com.day.api.provider.today.TodayProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/10/23 17:08
 */
@Tag(name = "昨天的控制层信息")
@RestController
public class YesterDayController {

    @DubboReference(group = DubboNacosGroup.TODAY_DUBBO_NACOS)
    private TodayProvider todayProvider;

    @Operation(description = "获取当前日期")
    @GetMapping(value = "getTodayDataTime")
    public String getTodayDataTime() {
        return todayProvider.getTodayDataTime();
    }


}
