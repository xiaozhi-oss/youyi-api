package com.youyi.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
@TableName("orders")
public class Order {

    /**
     * 订单id
     */
    @TableId("order_id")
    private String orderId;

    /**
     * 订单总金额
     */
    @TableField("total_price")
    private BigDecimal totalPrice;

    /**
     * 订单创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 购买数量
     */
    @TableField("totalNumber")
    private Integer totalNumber;

    /**
     * 地址表 id
     */
    @TableField("address_id")
    private Integer addressId;

    /**
     * 订单状态：0-未发货 1-已发货 2-确认收货 3-退货退款 4-仅退款 5-售后
     */
    @TableField("order_status")
    private Integer orderStatus;

    /**
     * 买家 id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 多个订单id
     */
    @TableField("product_ids")
    private String productIds;

    @TableField("logistics_tracking_number")
    private String logisticsTrackingNumber;

    @TableField("logistics_waybill_number")
    private String logisticsWaybillNumber;
}
