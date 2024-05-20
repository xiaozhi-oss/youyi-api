package com.youyi.api.service.serviceImpl;

import com.youyi.api.mapper.OrderMapper;
import com.youyi.api.model.entity.Order;
import com.youyi.api.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
