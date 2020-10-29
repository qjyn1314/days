package com.day.today.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.day.common.base.QueryRequest;
import com.day.today.persistence.entity.Order;
import com.day.today.persistence.mapper.OrderMapper;
import com.day.today.persistence.service.IOrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Service实现
 *
 * @author wangjunming
 * @since 2020-10-28 10:25:01
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

   /**
    * 分页列表
    *
    * @param queryRequest
    * @param order
    * @author wangjunming
    * @since 2020-10-28 10:25:01
    */
    @Override
    public IPage<Order> page(QueryRequest queryRequest, Order order) {
        LambdaQueryWrapper<Order> queryWrapper = initQueryWrapper(queryRequest,order);
        Page<Order> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return orderMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author wangjunming
    * @since 2020-10-28 10:25:01
    */
    private LambdaQueryWrapper<Order> initQueryWrapper(QueryRequest queryRequest, Order order) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param order
    * @author wangjunming
    * @since 2020-10-28 10:25:01
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
//    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean save(Order order) {
//        log.info("分布式事务ID是--1：{}", RootContext.getXID());
        //--TODO 做一些初始化动作
        final boolean b = orderMapper.insert(order) > 0;
//        log.info("分布式事务ID是--2：{}", RootContext.getXID());
        int i = 12/0;
//        log.info("分布式事务ID是--3：{}", RootContext.getXID());
        return b;
    }

   /**
    * 修改
    *
    * @param order
    * @author wangjunming
    * @since 2020-10-28 10:25:01
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Order order) {
        //--TODO 做一些效验动作
        return orderMapper.updateById(order)>0;
    }

   /**
    * 获取单个
    *
    * @param order
    * @author wangjunming
    * @since 2020-10-28 10:25:01
    */
    @Override
    public Order selOne(Order order) {
    LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return orderMapper.selectOne(queryWrapper);
    }


}
