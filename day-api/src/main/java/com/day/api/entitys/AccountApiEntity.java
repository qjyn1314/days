package com.day.api.entitys;

import java.io.Serializable;

/**
 * <p>
 * explain: 用户扣减余额需要的信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/8 14:29
 */
public class AccountApiEntity implements Serializable {

    private String userId;

    private Double amount;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
