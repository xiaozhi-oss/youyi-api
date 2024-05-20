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
@TableName("user_info")
public class UserInfo {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用户性别：1-男 2-女
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 用户头像
     */
    @TableField("url")
    private String url;

    /**
     * 0-正常 1-禁用 2-删除
     */
    @TableField("status")
    private Integer status;


}
