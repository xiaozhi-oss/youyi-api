package com.youyi.api.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.youyi.api.exception.LoginException;
import com.youyi.api.model.entity.UserInfo;
import com.youyi.api.utils.CacheUtil;
import com.youyi.api.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaozhi
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            throw new LoginException("重新登录~~~");
        }
        UserInfo userInfo = (UserInfo) CacheUtil.get(token);
        if (ObjectUtil.isNull(userInfo)) {
            throw new LoginException("重新登录~~~");
        }
        hostHolder.setUser(userInfo);
        return true;
    }
    
}
