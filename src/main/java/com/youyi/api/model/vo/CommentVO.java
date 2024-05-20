package com.youyi.api.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@Getter
@Setter
@TableName("comment")
public class CommentVO {
    
    private Integer id;
    private String username;
    private String content;
    private String avgUrl;
    private Date time;
    private Integer userId;
    private List<String> imgList;
    private Integer productId;

}
