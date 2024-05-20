package com.youyi.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("product")
public class Product {

    /**
     * 商品ID
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品图片
     */
    @TableField("url")
    private String url;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品描述
     */
    @TableField("miaoshu")
    private String miaoshu;

    /**
     * 商品价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 商品规格
     */
    @TableField("size")
    private String size;

    /**
     * 商品库存
     */
    @TableField("kucun")
    private Integer kucun;
    
    @TableField("create_time")
    private Date createTime;


}
