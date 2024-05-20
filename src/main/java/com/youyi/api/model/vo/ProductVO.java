package com.youyi.api.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
@TableName("ProductVO")
public class ProductVO {

    /**
     * 商品ID
     */
    private Integer id;

    /**
     * 商品图片
     */
    private String url;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String miaoshu;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品规格
     */
    private String[] sizes;

    /**
     * 商品库存
     */
    private Integer kucun;
}
