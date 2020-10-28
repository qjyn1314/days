package com.day.yesterday.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.day.common.base.QueryRequest;
import com.day.yesterday.persistence.entity.Account;
import com.day.yesterday.persistence.mapper.AccountMapper;
import com.day.yesterday.persistence.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Service实现
 *
 * @author wangjunming
 * @since 2020-10-28 10:29:08
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

   /**
    * 分页列表
    *
    * @param queryRequest
    * @param account
    * @author wangjunming
    * @since 2020-10-28 10:29:08
    */
    @Override
    public IPage<Account> page(QueryRequest queryRequest, Account account) {
        LambdaQueryWrapper<Account> queryWrapper = initQueryWrapper(queryRequest,account);
        Page<Account> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return accountMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author wangjunming
    * @since 2020-10-28 10:29:08
    */
    private LambdaQueryWrapper<Account> initQueryWrapper(QueryRequest queryRequest, Account account) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param account
    * @author wangjunming
    * @since 2020-10-28 10:29:08
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Account account) {
        //--TODO 做一些初始化动作
        return accountMapper.insert(account)>0;
    }

   /**
    * 修改
    *
    * @param account
    * @author wangjunming
    * @since 2020-10-28 10:29:08
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Account account) {
        //--TODO 做一些效验动作
        return accountMapper.updateById(account)>0;
    }

   /**
    * 获取单个
    *
    * @param account
    * @author wangjunming
    * @since 2020-10-28 10:29:08
    */
    @Override
    public Account selOne(Account account) {
    LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return accountMapper.selectOne(queryWrapper);
    }


}
