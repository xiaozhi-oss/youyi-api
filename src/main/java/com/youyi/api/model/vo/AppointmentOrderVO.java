package com.youyi.api.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@Data
public class AppointmentOrderVO {

    private Integer id;
    private String phone;
    private String name;
    private Integer status;
    private String mainicuristName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String projectName;
}
