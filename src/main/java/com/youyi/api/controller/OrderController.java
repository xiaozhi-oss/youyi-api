package com.youyi.api.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyi.api.model.dto.OrderAddRequest;
import com.youyi.api.model.dto.UpdateOrderStatusRequest;
import com.youyi.api.model.entity.Address;
import com.youyi.api.model.entity.Order;
import com.youyi.api.model.entity.OrderProductInfo;
import com.youyi.api.model.sfobj.RouteObj;
import com.youyi.api.model.vo.LogisticsInfoVO;
import com.youyi.api.model.vo.OrderVO;
import com.youyi.api.service.AddressService;
import com.youyi.api.service.OrderProductInfoService;
import com.youyi.api.service.OrderService;
import com.youyi.api.utils.HostHolder;
import com.youyi.api.utils.ResponseResult;
import com.youyi.api.utils.SFFastMailServiceUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@RestController
@RequestMapping("/youyi/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    
    @Resource
    private HostHolder hostHolder;
    
    @Resource
    private OrderProductInfoService orderProductInfoService;
    
    @Resource
    private AddressService addressService;
    
    @PostMapping("/createOrder")
    public ResponseResult<String> createOrder(@RequestBody OrderAddRequest orderAddRequest) {
        List<OrderProductInfo> products = orderAddRequest.getProducts();
        orderProductInfoService.saveBatch(products);
        StringBuilder ids = new StringBuilder();
        BigDecimal totalPrice = new BigDecimal(0);
        Integer totalNumber = 0;
        for (OrderProductInfo p : products) {
            ids.append(p.getId()).append("-");          // 商品ids
            totalPrice = totalPrice.add(p.getPrice());  // 总价格
            totalNumber += p.getNumber();               // 总数
        }
        Order order = new Order();
        order.setOrderId(String.valueOf(IdUtil.getSnowflakeNextId()));
        order.setAddressId(orderAddRequest.getAddressId());
        order.setTotalPrice(totalPrice);
        order.setTotalNumber(totalNumber);
        order.setCreateTime(new Date());
        order.setOrderStatus(0);
        order.setProductIds(ids.toString());
        order.setUserId(hostHolder.getUser().getId());
        orderService.save(order);
        SFFastMailServiceUtil.createOrder();    // 顺丰下单
        return ResponseResult.success();
    }
    
    @GetMapping("/list")
    public ResponseResult<List<OrderVO>> orderList(Integer current, Integer status) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        Page<Order> page = new Page<>(current, 10);
        if (status != null) {
            wrapper.eq("order_status", status);  
        }
        wrapper.orderByDesc("create_time");
        wrapper.eq("user_id", hostHolder.getUser().getId());
        List<Order> orderList = orderService.page(page, wrapper).getRecords();
        List<OrderVO> orderVOList = new ArrayList<>();
        orderList.forEach(order -> {
            OrderVO orderVO = new OrderVO();
            BeanUtil.copyProperties(order, orderVO );
            List<Integer> idList = Arrays.stream(order.getProductIds().split("-")).map(Integer::new).collect(Collectors.toList());
            List<OrderProductInfo> orderProductInfos = orderProductInfoService.listByIds(idList);
            orderVO.setProductInfoList(orderProductInfos);
            orderVOList.add(orderVO);
        });
        return ResponseResult.success(orderVOList);
    }

    @GetMapping("/allList")
    public ResponseResult<List<OrderVO>> allList(Integer current, Integer status) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        Page<Order> page = new Page<>(current, 10);
        if (status != null) {
            wrapper.eq("order_status", status);
        }
        wrapper.orderByDesc("create_time");
        List<Order> orderList = orderService.page(page, wrapper).getRecords();
        List<OrderVO> orderVOList = new ArrayList<>();
        orderList.forEach(order -> {
            OrderVO orderVO = new OrderVO();
            BeanUtil.copyProperties(order, orderVO );
            List<Integer> idList = Arrays.stream(order.getProductIds().split("-")).map(Integer::new).collect(Collectors.toList());
            List<OrderProductInfo> orderProductInfos = orderProductInfoService.listByIds(idList);
            orderVO.setProductInfoList(orderProductInfos);
            orderVOList.add(orderVO);
        });
        return ResponseResult.success(orderVOList);
    }
    
    @PostMapping("/updateOrderStatus")
    public ResponseResult<String> updateOrderStatus(
            @Valid @RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) {
        // 确认发货就给物流公司下单
        String obtainWaybillNumber = "";
        if (updateOrderStatusRequest.getOrderStatus() == 1) {
            // 获取运单号
            obtainWaybillNumber = SFFastMailServiceUtil.obtainWaybillNumber();
        }
        Order order = BeanUtil.copyProperties(updateOrderStatusRequest, Order.class);
        order.setLogisticsWaybillNumber(obtainWaybillNumber);
        orderService.updateById(order);
        return ResponseResult.success();
    }
    
    @GetMapping("/getLogisticsInfo")
    public ResponseResult<LogisticsInfoVO> updateOrderStatus(String orderId) {
        Order order = orderService.getById(orderId);
        String waybillNumber = order.getLogisticsWaybillNumber();
        Integer addressId = order.getAddressId();
        Address address = addressService.getById(addressId);
        LogisticsInfoVO infoVO = new LogisticsInfoVO();
        String addressInfo = address.getRecipient() + "，" +
                address.getPhoneNumber() + "，" +
                address.getAddress();
        infoVO.setAddressInfo(addressInfo);
        List<RouteObj> list = SFFastMailServiceUtil.getLogisticsInfoRoute();
        infoVO.setRouteList(list);
        infoVO.setWaybillNumber(waybillNumber);
        return ResponseResult.success(infoVO);
    }
}

