package com.youyi.api.model.vo;

import com.youyi.api.model.sfobj.RouteObj;
import lombok.Data;

import java.util.List;

/**
 * @author xiaozhi
 */
@Data
public class LogisticsInfoVO {
    private String addressInfo;
    private List<RouteObj> routeList;
    private String waybillNumber;
}