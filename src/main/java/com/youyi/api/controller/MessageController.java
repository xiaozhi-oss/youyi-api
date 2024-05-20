package com.youyi.api.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youyi.api.model.entity.Message;
import com.youyi.api.model.entity.UserInfo;
import com.youyi.api.model.vo.MessageVO;
import com.youyi.api.model.vo.MessageVO2;
import com.youyi.api.service.MessageService;
import com.youyi.api.service.UserInfoService;
import com.youyi.api.utils.HostHolder;
import com.youyi.api.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YOUYI
 * @since 2023-12-15 12:13:16
 */
@RestController
@RequestMapping("/youyi/message")
public class MessageController {
    @Resource
    private MessageService messageService;
    @Resource
    private UserInfoService userInfoService;
    
    @Resource
    private HostHolder hostHolder;
    
    @GetMapping("/chatList")
    public ResponseResult<List<MessageVO>> chatList(){
        List<Message> allMessageList = messageService.getAllMessageList();
        List<MessageVO> allMessageVOList = new ArrayList<>();
        for (Message message : allMessageList) {
            MessageVO messageVO = BeanUtil.copyProperties(message, MessageVO.class);
            long unread = 0;
            if (!hostHolder.getUser().getId().equals(message.getFromId())) {
                QueryWrapper<Message> wrapper = new QueryWrapper<>();
                wrapper.eq("from_id", message.getFromId());
                wrapper.eq("status", 0);
                unread = messageService.count(wrapper);
            }
            messageVO.setUnreadCount(unread);
            UserInfo userInfo = userInfoService.getById(message.getFromId());
            messageVO.setUsername(userInfo.getUsername());
            messageVO.setUrl(userInfo.getUrl());
            allMessageVOList.add(messageVO);
        }
        return ResponseResult.success(allMessageVOList);
    }
    @GetMapping("/getMessageByConversationId")
    public ResponseResult<List<MessageVO2>> getMessageByConversationId(String conversationId){
        
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.eq("conversation_id", conversationId);
        wrapper.orderByAsc("send_time");
        List<Message> allMessageList = messageService.list(wrapper);
        List<MessageVO2> allMessageVOList = new ArrayList<>();
        UserInfo userInfo = hostHolder.getUser();
        for (Message message : allMessageList) {
            MessageVO2 messageVO2 = BeanUtil.copyProperties(message, MessageVO2.class);
            Integer fromId = message.getToId();
            if (Objects.equals(fromId, userInfo.getId())) {
                messageVO2.setUsername(userInfo.getUsername());
                messageVO2.setUrl(userInfo.getUrl());
            } else {
                UserInfo info = userInfoService.getById(message.getFromId());
                messageVO2.setUsername(info.getUsername());
                messageVO2.setUrl(info.getUrl());
            }
            allMessageVOList.add(messageVO2);
        }
        return ResponseResult.success(allMessageVOList);
    }
}

