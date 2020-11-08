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


    /**
     * 用于测试分布式事务，业务为根据用户ID扣减相应的余额
     *
     * @param account 扣减金额
     * @author wangjunming
     * @since 2020/11/8 14:18
     */
    Account updataAmountByUserId(Account account);


}
