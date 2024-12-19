package com.day.yesterday.persistence.entity;


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
 * @date 2020-10-28 10:29:08
 */
@Data
@TableName("account")
@Schema(name = "Account", description = "Account对象")
public class Account implements Serializable {

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
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @Schema(name = "")
    @TableField("user_id")
    private String userId;

}
