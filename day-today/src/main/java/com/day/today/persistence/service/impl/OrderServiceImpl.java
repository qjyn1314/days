package com.day.today.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.day.api.config.DubboNacosGroup;
import com.day.api.entitys.AccountApiEntity;
import com.day.api.provider.yesterday.YesterdayProvider;
import com.day.common.base.QueryRequest;
import com.day.today.persistence.entity.Order;
import com.day.today.persistence.mapper.OrderMapper;
import com.day.today.persistence.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现
 *
 * @author wangjunming
 * @since 2020-10-28 10:25:01
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @DubboReference(group = DubboNacosGroup.YESTERDAY_DUBBO_NACOS, version = "1.0.0")
    private YesterdayProvider yesterdayProvider;


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
        LambdaQueryWrapper<Order> queryWrapper = initQueryWrapper(queryRequest, order);
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
//        int i = 12/0;
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
        return orderMapper.updateById(order) > 0;
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

    /**
     * 用于测试分布式事务功能
     *
     * @param order
     * @author wangjunming
     * @since 2020/11/8 14:46
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public boolean saveOrder(Order order) {
        log.info("开始创建订单");
        final boolean save = save(order);
        boolean updataAmount = false;
        if (save) {
            log.info("完成创建订单");
            log.info("开始扣减余额");
            AccountApiEntity accountApiEntity = new AccountApiEntity();
            accountApiEntity.setUserId(order.getUserId());
            accountApiEntity.setAmount(order.getAmount());
            updataAmount = yesterdayProvider.updataAmountByUserId(accountApiEntity);
            log.info("完成扣减余额");
        }
        log.info("开始更新订单状态");
//        int i = 12/0;
        boolean update = false;
        if (updataAmount) {
            order.setStatus(Integer.valueOf("1"));
            update = update(order);
            log.info("完成更新订单状态");
        }
        return save && updataAmount && update;
    }


}
