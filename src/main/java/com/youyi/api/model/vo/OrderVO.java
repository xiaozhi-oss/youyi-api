package com.youyi.api.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youyi.api.model.entity.OrderProductInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author xiaozhi
 */
@Data
public class OrderVO {
    private String orderId;
    private BigDecimal totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer totalNumber;
    private Integer addressId;
    private Integer orderStatus;
    private List<OrderProductInfo> productInfoList;
    private String logisticsTrackingNumber;
    private String logisticsWaybillNumber;
}
