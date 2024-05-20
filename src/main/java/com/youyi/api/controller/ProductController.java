package com.youyi.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youyi.api.model.entity.Product;
import com.youyi.api.model.vo.ProductVO;
import com.youyi.api.service.ProductService;
import com.youyi.api.utils.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@RestController
@RequestMapping("/youyi/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @GetMapping("/getProductList")
    public ResponseResult<List<ProductVO>> getProductList(Integer current,
                                                          String searchKey,
                                                          HttpServletRequest request) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(searchKey)) {
            wrapper.like("name", "%" + searchKey + "%");
        }
        wrapper.orderByDesc("create_time");
        List<Product> productList = productService.page(new Page<>(current, 10), wrapper).getRecords();
        List<ProductVO> productVOS = new ArrayList<>();
        productList.forEach(p -> {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(p, productVO);
            String[] sizes = p.getSize().split("-");
            productVO.setSizes(sizes);
            productVOS.add(productVO);
        });
        return ResponseResult.success(productVOS);
    }

    @PostMapping("/delete")
    public ResponseResult<String> deleteProduct(@RequestBody Product product) {
        productService.removeById(product);
        return ResponseResult.success();
    }

    @PostMapping("/addAndUpdate")
    public ResponseResult<String> updateProduct(@RequestBody Product product) {
        product.setCreateTime(new Date());
        productService.saveOrUpdate(product);
        return ResponseResult.success();
    }
}

