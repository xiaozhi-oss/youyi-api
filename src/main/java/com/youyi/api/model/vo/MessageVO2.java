package com.youyi.api.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-15 12:13:16
 */
@Data
public class MessageVO2 {
    
    private String content;
    private Integer toId;
    private Integer fromId;
    private String conversationId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
    private String url;
    private String username;
}
