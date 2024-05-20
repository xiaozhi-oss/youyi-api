package com.youyi.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author YOUYI
 * @since 2023-12-16 04:30:39
 */
@Getter
@Setter
@TableName("order_product_info")
public class OrderProductInfo {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名
     */
    @TableField("product_name")
    private String name;

    /**
     * 商品价格
     */
    @TableField("product_price")
    private BigDecimal price;

    /**
     * 商品尺寸
     */
    @TableField("product_size")
    private String size;

    /**
     * 商品图片
     */
    @TableField("product_url")
    private String url;

    @TableField("product_miaoshu")
    private String miaoshu;

    @TableField("product_number")
    private Integer number;

    @TableField("product_id")
    private Integer productId;
    
    @TableField("review_status")
    private Integer reviewStatus;
}
