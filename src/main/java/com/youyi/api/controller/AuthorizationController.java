package com.youyi.api.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youyi.api.exception.DataException;
import com.youyi.api.model.dto.LoginRequest;
import com.youyi.api.model.dto.RegisterRequest;
import com.youyi.api.model.entity.Authorization;
import com.youyi.api.model.entity.UserInfo;
import com.youyi.api.service.AuthorizationService;
import com.youyi.api.service.UserInfoService;
import com.youyi.api.utils.CacheUtil;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

/**
 * 授权
 * @author YOUYI
 * @since 2023-12-13 09:01:07
 */
@RestController
@RequestMapping("/youyi/authorization")
public class AuthorizationController {
    
    @Resource
    private AuthorizationService authorizationService;
    
    @Resource
    private UserInfoService userInfoService;
    
    private final String[] avgUrls = {
            "Princess", "Mimi", "Tigger", "Zoe", "Muffin", 
            "Bear", "Callie", "Bailey", "Precious", "Oscar"
    };
    private final String BASE_AVG_URL = "https://api.dicebear.com/7.x/adventurer/svg?seed=";
    
    @PostMapping("/login")
    public ResponseResult<String> login(@Valid @RequestBody LoginRequest loginRequest) throws DataException {
        QueryWrapper<Authorization> wrapper = new QueryWrapper<>();
        wrapper.eq(true, "IDENTIFIER", loginRequest.getUsername());
        wrapper.eq(true, "CREDENTIAL", loginRequest.getPassword());
        Authorization authorization = authorizationService.getOne(wrapper);
        if (authorization == null) {
            throw new DataException("用户名或密码错误");
        }
        QueryWrapper<UserInfo> userInfoWrapper = new QueryWrapper<>();
        userInfoWrapper.eq("id", authorization.getUserId());
        UserInfo userinfo = userInfoService.getOne(userInfoWrapper);
        // 生成 token 返回
        String token = UUID.randomUUID().toString();
        // 放入缓存中
        CacheUtil.put(token, userinfo);
        return ResponseResult.success(token);
    }

    @PostMapping("/register")
    public ResponseResult<String> register(@Valid @RequestBody RegisterRequest registerRequest) throws DataException {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new DataException("两次密码不一致");
        }
        // 判断是否已经存在该用户
        QueryWrapper<Authorization> wrapper = new QueryWrapper<>();
        wrapper.eq(true, "IDENTIFIER", registerRequest.getUsername());
        Authorization authorization = authorizationService.getOne(wrapper);
        if (authorization != null) {
            throw new DataException("用户名已存在");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUrl(BASE_AVG_URL + avgUrls[RandomUtil.randomInt(0, avgUrls.length)]);
        userInfo.setUsername(RandomUtil.randomString(6));
        userInfo.setStatus(0);
        userInfo.setSex(2);
        userInfoService.save(userInfo);
        authorization = new Authorization();
        authorization.setUserId(userInfo.getId());
        authorization.setIdentitytype(0);
        authorization.setIdentifier(registerRequest.getUsername());
        authorization.setCredential(registerRequest.getPassword());
        authorizationService.save(authorization);
        return ResponseResult.success();
    }
    
    @PostMapping("/logout")
    public ResponseResult<String> logout(HttpServletRequest request) throws DataException {
        String token = request.getParameter("Authorization");
        CacheUtil.remove(token);
        return ResponseResult.success();
    }
}

