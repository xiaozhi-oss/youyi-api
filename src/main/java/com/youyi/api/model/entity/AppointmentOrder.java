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
@TableName("appointment_order")
public class AppointmentOrder {

    /**
     * 预约订单编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 预约电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 预约人姓名
     */
    @TableField("name")
    private String name;

    /**
     * 预约状态：0 - 已预约 1-已确认 2-已完成
     */
    @TableField("status")
    private Integer status;

    /**
     * 美甲师ID
     */
    @TableField("mainicurist_id")
    private Integer mainicuristId;

    /**
     * 买家ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 预约时间
     */
    @TableField("date")
    private Date date;

    /**
     * 项目 id
     */
    @TableField("project_id")
    private Integer projectId;
    
}
