package com.youyi.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@Getter
@Setter
@TableName("address")
public class Address {

    /**
     * 主键ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 归属用户
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 是否为默认
     */
    @TableField("default_index")
    private Boolean defaultIndex;

    /**
     * 收件人
     */
    @TableField("recipient")
    private String recipient;

    /**
     * 收件人号码
     */
    @TableField("phoneNumber")
    private String phoneNumber;

    /**
     * 地址
     */
    @TableField("address")
    private String address;


}
