package com.youyi.api.utils;

import com.youyi.api.model.entity.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author YOUYI
 */
@Component
public class HostHolder {

    private ThreadLocal<UserInfo> users = new ThreadLocal<>();

    public void setUser(UserInfo user) {
        users.set(user);
    }

    public UserInfo getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}