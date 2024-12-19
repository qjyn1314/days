package com.day.yesterday.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.day.common.base.QueryRequest;
import com.day.yesterday.persistence.entity.Account;
import com.day.yesterday.persistence.mapper.AccountMapper;
import com.day.yesterday.persistence.service.IAccountService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Service实现
 *
 * @author wangjunming
 * @since 2020-10-28 10:29:08
 */
@Slf4j
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
        if(StringUtils.isNotBlank(account.getUserId())){
            queryWrapper.eq(Account::getUserId,account.getUserId());
        }
        return accountMapper.selectOne(queryWrapper);
    }

    /**
     * 用于测试分布式事务，业务为根据用户ID扣减相应的余额
     *
     * @param account 扣减金额
     * @author wangjunming
     * @since 2020/11/8 14:18
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Account updataAmountByUserId(Account account){
        final Account selOneAccount = selOne(account);
        if (selOneAccount == null) {
            log.error("余额不足！");
            return null;
        }
        if (account.getAmount().compareTo(selOneAccount.getAmount()) > 0) {
            log.error("余额不足！");
            return null;
        }
        final double amountPoor = selOneAccount.getAmount() - account.getAmount();
        selOneAccount.setAmount(amountPoor);
        final boolean update = accountMapper.updateById(selOneAccount) > 0;
//        int i = 12/0;
        return update ? selOneAccount : null;
    }


}
