package com.day.today.persistence.entity;


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
 * @date 2020-10-28 10:25:01
 */
@Data
@TableName("t_order")
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

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
    @TableField("commodity_code")
    private String commodityCode;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableField("count")
    private Integer count;

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
    @TableField("order_no")
    private String orderNo;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    @TableField("user_id")
    private String userId;

}
