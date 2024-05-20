package com.youyi.api.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xiaozhi
 */
@Data
public class UpdateOrderStatusRequest {
    @NotBlank(message = "订单号不能为空")
    private String orderId;
    @NotNull(message = "状态不能为空")
    private Integer orderStatus;
}
