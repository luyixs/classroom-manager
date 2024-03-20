package com.example.demo_course_selection.components;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo_course_selection.dto.Message;
import com.example.demo_course_selection.service.SignInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/signin/{uId}")
@Component
public class SignInServer {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SignInService signInService;

    /**
     * 数据传输格式：String {"isMassMsg":false,"from":"A","to":"B","text":"你好"}
     */

    public static final Logger log = LoggerFactory.getLogger(SignInServer.class);
    public static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();




    @OnOpen
    public void onOpen(Session session,@PathParam("uId")String uId){
        sessionMap.put(uId, session);
        log.info("在线人数：{}，{}已连接",sessionMap.size(),uId);
        JSONArray users = new JSONArray();
        for(Object key : sessionMap.keySet()){
            users.add(JSONUtil.createObj().set("uId",key));
        }
//        redisTemplate.opsForValue()

//        sendAllMessage(new JSONObject().set("users",users).toString());

    }

    @OnClose
    public void onClose(Session session,@PathParam("uId")String uId){
        sessionMap.remove(uId);
        log.info("在线人数：{}，{}已断开",sessionMap.size(),uId);
        JSONArray users = new JSONArray();
        for(Object key : sessionMap.keySet()){
            users.add(JSONUtil.createObj().set("uId",key));
        }
        sendAllMessage(new JSONObject().set("users",users).toString());

    }
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("uId") String uId){
        log.info("[{}]向服务端发送消息:{}", uId, message);
        //{"isMassMsg":false,"from":"A","to":"B","text":"你好"}
        Message receive = JSONUtil.toBean(message, Message.class);
        if (receive.isMassMsg()){
            sendAllMessage(message);  //群发
        }else {
            sendMessage(message,sessionMap.get(receive.getTo())); //单发
        }

    }
    @OnError
    public void onError(Session session,Throwable err){
        log.error(err.getMessage());
        err.printStackTrace();

    }
    /**
     * 服务端发送消息给客户端
     */
    public void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端向[{}]发送消息{}", toSession, message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    public void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端向[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

}
