package com.youyi.api.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author YOUYI
 */
@Data
public class ManicuristVO {
    private Integer id;
    private String name;
    private Integer kucun;
    private Integer employmentTime;
    private List<String> workList;
    private Integer reservationCount;
    private String url;
    private Integer good;
    
}
