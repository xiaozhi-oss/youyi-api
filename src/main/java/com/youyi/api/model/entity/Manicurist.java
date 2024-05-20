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
@TableName("manicurist")
public class Manicurist {

    /**
     * 美甲师ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 美甲师姓名
     */
    @TableField("name")
    private String name;

    @TableField("kucun")
    private Integer kucun;
    
    /**
     * 从业时间
     */
    @TableField("employment_time")
    private Integer employmentTime;

    /**
     * 作品集
     */
    @TableField("works")
    private String works;

    /**
     * 预约人数
     */
    @TableField("reservation_count")
    private Integer reservationCount;

    /**
     * 美甲师头像
     */
    @TableField("url")
    private String url;

    /**
     * 好评数
     */
    @TableField("good")
    private Integer good;


}
