package com.youyi.api.utils;

import cn.hutool.cache.Cache;
import com.youyi.api.model.entity.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author YOUYI
 */
@Component
public class CacheUtil {
    public static Cache<String,Object> fifoCache = cn.hutool.cache.CacheUtil.newFIFOCache(1000);
    static {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test");
        userInfo.setId(12);
        userInfo.setSex(0);
        userInfo.setUrl(" https://api.dicebear.com/7.x/adventurer/svg?seed=Precious");
        userInfo.setPhoneNumber("119");
        userInfo.setStatus(0);
        fifoCache.put("aecd782c-d7ed-4612-9991-feb2ffbfcc35", userInfo);
    }
    
    public static void put(String key, Object value) {
        fifoCache.put(key, value);
    }
    public static Object get(String key) {
        return fifoCache.get(key);
    }
    
    public static void remove(String key) {
        fifoCache.remove(key);
    }
}
