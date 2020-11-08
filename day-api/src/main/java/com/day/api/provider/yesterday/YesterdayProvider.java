package com.day.api.provider.yesterday;

import com.day.api.entitys.AccountApiEntity;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/10/23 17:22
 */
public interface YesterdayProvider {

    String getYesterDayTime();

    /**
     * 用于测试分布式事务
     *
     * @author wangjunming
     * @since 2020/11/8 14:52
     */
    boolean updataAmountByUserId(AccountApiEntity accountApiEntity);


}
