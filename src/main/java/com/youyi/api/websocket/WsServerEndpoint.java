package com.youyi.api.websocket;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyi.api.model.entity.Message;
import com.youyi.api.model.vo.MessageVO2;
import com.youyi.api.service.MessageService;
import com.youyi.api.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YOUYI
 */
@ServerEndpoint("/youyi/chat")
@Component
@Slf4j
public class WsServerEndpoint {
    
    public static ConcurrentHashMap<Integer, Session> sessionMap = new ConcurrentHashMap<>();
    
    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) throws UnsupportedEncodingException {
        log.info("连接成功");
        String query = session.getRequestURI().getQuery();
        Map<String, String> params = splitQuery(query);
        String userId = params.get("userId");
        sessionMap.put(Integer.parseInt(userId), session);
    }

    private Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public void onMsg(String text) throws IOException {
        String jsonText = text;
        ObjectMapper mapper = new ObjectMapper();
        MessageVO2 messageVO2 = mapper.readValue(text, MessageVO2.class);
        Session session = sessionMap.get(messageVO2.getToId());
        if (ObjectUtils.isNotEmpty(session)) {
            session.getBasicRemote().sendText(jsonText);
        }
        Message message = BeanUtil.copyProperties(messageVO2, Message.class);
        message.setStatus(0);
        message.setSendTime(new Date());
        MessageService messageService = SpringContextUtil.getApplicationContext().getBean(MessageService.class);
        messageService.save(message);
    }
}