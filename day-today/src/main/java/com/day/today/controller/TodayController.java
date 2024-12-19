package com.day.today.controller;

import com.day.api.config.DubboNacosGroup;
import com.day.api.provider.yesterday.YesterdayProvider;
import com.day.common.base.JsonResult;
import com.day.today.persistence.entity.Order;
import com.day.today.persistence.service.IOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/19 10:11
 */
@RestController
@RequestMapping("/today")
public class TodayController {
    private static final Logger log = LoggerFactory.getLogger(TodayController.class);

    @DubboReference(group = DubboNacosGroup.YESTERDAY_DUBBO_NACOS,version = "2.0.0")
    private YesterdayProvider yesterdayProvider;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "testDistributedTransaction", method = RequestMethod.POST)
    public JsonResult testDistributedTransaction() {
        Order order = new Order();
        order.setUserId("1001");
        order.setAmount(300d);
        return JsonResult.success(orderService.saveOrder(order));
    }

    @GetMapping(value = "getYesterDayTime")
    public JsonResult getYesterDayTime() {
        return JsonResult.success(yesterdayProvider.getYesterDayTime());
    }


}