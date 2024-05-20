package com.youyi.api.service.serviceImpl;

import com.youyi.api.mapper.ProductMapper;
import com.youyi.api.model.entity.Product;
import com.youyi.api.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
