package com.youyi.api.controller;


import com.youyi.api.model.entity.UserInfo;
import com.youyi.api.service.UserInfoService;
import com.youyi.api.utils.HostHolder;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@RestController
@RequestMapping("/youyi/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;
    
    @Resource
    private HostHolder hostHolder;
    
    @GetMapping("/getUserInfo")
    public ResponseResult<UserInfo> getUserInfo(){
        return ResponseResult.success(hostHolder.getUser());
    }
}

