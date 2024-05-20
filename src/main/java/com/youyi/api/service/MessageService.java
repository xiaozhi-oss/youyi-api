package com.youyi.api.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youyi.api.model.entity.Message;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-15 12:13:16
 */
public interface MessageService extends IService<Message> {

    List<Message> getAllMessageList();
}
