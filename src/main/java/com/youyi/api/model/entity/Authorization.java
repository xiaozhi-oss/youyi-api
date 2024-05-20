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
@TableName("authorization")
public class Authorization {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 登录类别
     */
    @TableField("IDENTITYTYPE")
    private Integer identitytype;

    /**
     * 身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；
     */
    @TableField("IDENTIFIER")
    private String identifier;

    /**
     * 站内账号是密码、第三方登录是Token；
     */
    @TableField("CREDENTIAL")
    private String credential;
}
