package com.day.today.persistence.controller;

import com.day.common.base.BaseController;
import com.day.common.base.JsonResult;
import com.day.common.base.QueryRequest;
import com.day.today.persistence.entity.Order;
import com.day.today.persistence.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * Controller
 * </p>
 *
 * @author wangjunming
 * @since 2020-10-28 10:25:01
 */
@Slf4j
@Tag(name = "订单 Controller")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService service;

    @Operation(description = "分页列表")
    @GetMapping("/page")
    public JsonResult page(QueryRequest queryRequest, Order order) {
        return JsonResult.success(getDataTable(service.page(queryRequest, order)));
    }

    @Operation(description = "添加")
    @PostMapping("/save")
    public JsonResult save(Order order) {
        return JsonResult.success(service.save(order));
    }

    @Operation(description = "修改")
    @PostMapping("/update")
    public JsonResult update(Order order) {
        return JsonResult.success(service.update(order));
    }

    @Operation(description = "获取")
    @GetMapping("/selOne")
    public JsonResult selOne(Order order) {
        return JsonResult.success(service.selOne(order));
    }

}
