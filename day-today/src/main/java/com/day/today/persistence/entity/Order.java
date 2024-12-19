package com.day.today.persistence.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * Entity
 *
 * @author wangjunming
 * @date 2020-10-28 10:25:01
 */
@Data
@TableName("t_order")
@Schema(name = "Order对象", description = "")
public class Order implements Serializable {

    /**
     *
     */
    @Schema(name = "")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @Schema(name = "")
    @TableField("amount")
    private Double amount;

    /**
     *
     */
    @Schema(name = "")
    @TableField("commodity_code")
    private String commodityCode;

    /**
     * 状态
     */
    @Schema(name = "")
    @TableField("status")
    private Integer status;

    /**
     *
     */
    @Schema(name = "")
    @TableField("order_no")
    private String orderNo;

    /**
     *
     */
    @Schema(name = "")
    @TableField("user_id")
    private String userId;

}
