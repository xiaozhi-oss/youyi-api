package com.youyi.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youyi.api.model.entity.Address;
import com.youyi.api.service.AddressService;
import com.youyi.api.utils.HostHolder;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@RestController
@RequestMapping("/youyi/address")
public class AddressController {
    
    @Resource
    private AddressService addressService;
    
    @Resource
    private HostHolder hostHolder;
    
    @PostMapping("add")
    public ResponseResult<Address> add() {
       return null; 
    }

    @GetMapping("list")
    public ResponseResult<List<Address>> list() {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq(true, "user_id", hostHolder.getUser().getId());
        List<Address> list = addressService.list(wrapper);
        return ResponseResult.success(list);
    }

    @GetMapping("/getDefaultAddress")
    public ResponseResult<Address> getDefaultAddress() {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", hostHolder.getUser().getId())
                .eq("default_index", 1);
        Address address = addressService.getOne(wrapper);
        return ResponseResult.success(address);
    }
    
    @PostMapping("update")
    public ResponseResult<String> update() {
        return ResponseResult.success();
    }

    @PostMapping("delete")
    public ResponseResult<String> delete() {
        return ResponseResult.success();
    }
    
}

