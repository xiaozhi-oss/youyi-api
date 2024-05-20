package com.youyi.api.model.dto;

import com.youyi.api.model.entity.OrderProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author xiaozhi
 */
@Data
public class OrderAddRequest {
    private List<OrderProductInfo> products;
    private Integer addressId;
}
