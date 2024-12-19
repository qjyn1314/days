package com.day.yesterday.provider.v2;

import com.day.api.config.DubboNacosGroup;
import com.day.api.entitys.AccountApiEntity;
import com.day.api.provider.yesterday.YesterdayProvider;
import com.day.yesterday.persistence.entity.Account;
import com.day.yesterday.persistence.service.IAccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * explain: yesterday的dubbo服务具体实现
 * </p>
 *
 * @author wangjunming
 * @since 2020/10/23 17:23
 */
@Service
@DubboService(group = DubboNacosGroup.YESTERDAY_DUBBO_NACOS, version = "2.0.0")
public class YesterdayProviderImplV2 implements YesterdayProvider {

    @Autowired
    private IAccountService accountService;

    @Override
    public String getYesterDayTime() {
        return "现在的时间是-->" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

    /**
     * 用于测试分布式事务
     *
     * @param accountApiEntity 扣减金额
     * @author wangjunming
     * @since 2020/11/8 14:52
     */
    @Override
    public boolean updataAmountByUserId(AccountApiEntity accountApiEntity) {
        Account account = new Account();
        account.setUserId(accountApiEntity.getUserId());
        account.setAmount(accountApiEntity.getAmount());
        return accountService.updataAmountByUserId(account) != null;
    }


}
