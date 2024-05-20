package com.youyi.api.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("manicure_project")
public class ManicureProject {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名
     */
    @TableField("name")
    private String name;

    /**
     * 新价格
     */
    @TableField("new_price")
    private Double newPrice;

    /**
     * 旧价格
     */
    @TableField("old_price")
    private Double oldPrice;

    /**
     * 商品描述
     */
    @TableField("miaoshu")
    private String miaoshu;

    /**
     * 项目图片
     */
    @TableField("url")
    private String url;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
}
