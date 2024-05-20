package com.youyi.api.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youyi.api.mapper.MessageMapper;
import com.youyi.api.model.entity.Message;
import com.youyi.api.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-15 12:13:16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    
    @Override
    public List<Message> getAllMessageList() {
        return messageMapper.getAllMessageList();
    }
}
