package com.day.yesterday.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.day.common.base.QueryRequest;
import com.day.yesterday.persistence.entity.Account;

/**
 *  Service接口
 *
 * @author wangjunming
 * @since 2020-10-28 10:29:08
 */
public interface IAccountService {

    /**
     * 分页列表
     *
     * @author wangjunming
     * @since 2020-10-28 10:29:08
     */
    IPage<Account> page(QueryRequest queryRequest, Account account);

    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020-10-28 10:29:08
     */
     boolean save(Account account);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020-10-28 10:29:08
     */
     boolean update(Account account);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020-10-28 10:29:08
     */
    Account selOne(Account account);


}
