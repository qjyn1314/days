package com.day.yesterday.persistence.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *  Entity
 *
 * @author wangjunming
 * @date 2020-10-28 10:29:08
 */
@Data
@TableName("account")
@ApiModel(value="Account对象", description="")
public class Account implements Serializable {

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableField("amount")
    private Double amount;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableField("user_id")
    private String userId;

}
