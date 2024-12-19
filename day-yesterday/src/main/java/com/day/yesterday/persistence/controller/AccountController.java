package com.day.yesterday.persistence.controller;

import com.day.common.base.BaseController;
import com.day.common.base.JsonResult;
import com.day.common.base.QueryRequest;
import com.day.yesterday.persistence.entity.Account;
import com.day.yesterday.persistence.service.IAccountService;
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
 * @since 2020-10-28 10:29:08
 */
@Slf4j
@Tag(name = "账户 Controller")
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    private IAccountService service;

    @Operation(description = "分页列表")
    @GetMapping("/page")
    public JsonResult page(QueryRequest queryRequest, Account account) {
        return JsonResult.success(getDataTable(service.page(queryRequest, account)));
    }

    @Operation(description = "添加")
    @PostMapping("/save")
    public JsonResult save(Account account) {
        return JsonResult.success(service.save(account));
    }

    @Operation(description = "修改")
    @PostMapping("/update")
    public JsonResult update(Account account) {
        return JsonResult.success(service.update(account));
    }

    @Operation(description = "获取")
    @GetMapping("/selOne")
    public JsonResult selOne(Account account) {
        return JsonResult.success(service.selOne(account));
    }

}
