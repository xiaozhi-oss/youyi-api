package com.youyi.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
@TableName("comment")
public class Comment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 买家姓名
     */
    @TableField("username")
    private String username;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 用户头像链接
     */
    @TableField("avg_url")
    private String avgUrl;

    /**
     * 评论时间
     */
    @TableField("time")
    private Date time;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 评论图片id列表
     */
    @TableField("img_ids")
    private String imgIds;

    @TableField("product_id")
    private Integer productId;

}
