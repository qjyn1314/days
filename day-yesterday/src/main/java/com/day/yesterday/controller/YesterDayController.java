package com.day.yesterday.controller;

import com.day.api.config.DubboNacosGroup;
import com.day.api.provider.today.TodayProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/10/23 17:08
 */
@Api(tags = "昨天的控制层信息")
@RestController
public class YesterDayController {

    @DubboReference(group = DubboNacosGroup.TODAY_DUBBO_NACOS)
    private TodayProvider todayProvider;

    @ApiOperation(value = "获取当前日期")
    @GetMapping(value = "download")
    public String download(String name, HttpServletResponse response) {
        return todayProvider.getTodayDataTime();
    }


}
