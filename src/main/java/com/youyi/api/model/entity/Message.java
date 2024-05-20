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
 * @since 2023-12-15 12:13:16
 */
@Getter
@Setter
@TableName("message")
public class Message {

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发送用户ID
     */
    @TableField("from_id")
    private Integer fromId;

    /**
     * 接收用户ID
     */
    @TableField("to_id")
    private Integer toId;

    /**
     * 频道ID
     */
    @TableField("conversation_id")
    private String conversationId;

    /**
     * 消息状态：0-未读 1-已读
     */
    @TableField("status")
    private Integer status;

    /**
     * 发送时间
     */
    @TableField("send_time")
    private Date sendTime;

    /**
     * 发送内容
     */
    @TableField("content")
    private String content;


}
