package com.day.today.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.day.common.base.QueryRequest;
import com.day.today.persistence.entity.Order;

/**
 *  Service接口
 *
 * @author wangjunming
 * @since 2020-10-28 10:25:01
 */
public interface IOrderService {

    /**
     * 分页列表
     *
     * @author wangjunming
     * @since 2020-10-28 10:25:01
     */
    IPage<Order> page(QueryRequest queryRequest, Order order);

    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020-10-28 10:25:01
     */
     boolean save(Order order);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020-10-28 10:25:01
     */
     boolean update(Order order);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020-10-28 10:25:01
     */
    Order selOne(Order order);


}
