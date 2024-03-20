package com.example.demo_course_selection.components;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo_course_selection.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/ws/{uId}")
@Component
public class MyWebSocketServer {

    /**
     * 数据传输格式：String {"isMassMsg":false,"from":"A","to":"B","text":"你好"}
     */

    private static final Logger log = LoggerFactory.getLogger(MyWebSocketServer.class);
    private static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();



    @OnOpen
    public void onOpen(Session session,@PathParam("uId")String uId){
        sessionMap.put(uId, session);
        log.info("在线人数：{}，{}已连接",sessionMap.size(),uId);
        JSONArray users = new JSONArray();
        for(Object key : sessionMap.keySet()){
            users.add(JSONUtil.createObj().set("uId",key));
        }
        sendAllMessage(new JSONObject().set("users",users).toString());

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
    private void sendMessage(String message, Session toSession) {
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
    private void sendAllMessage(String message) {
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



//    @OnMessage
//    public void onMessage(String message, Session session, @PathParam("uId") String uId){
//        log.info("服务端收到用户uId={}的消息:{}", uId, message);
//        JSONObject obj = JSONUtil.parseObj(message);
//        String touId = obj.getStr("to"); // to表示发送给哪个用户，比如 admin
//        String text = obj.getStr("text"); // 发送的消息文本  hello
//        // {"to": "admin", "text": "聊天文本"}
//        Session toSession = sessionMap.get(touId); // 根据 to用户名来获取 session，再通过session发送消息文本
//        if (toSession != null) {
//            // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
//            // {"from": "zhang", "text": "hello"}
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.set("from", uId);  // from 是 zhang
//            jsonObject.set("text", text);  // text 同上面的text
//            this.sendMessage(jsonObject.toString(), toSession);
//            log.info("发送给用户uId={}，消息：{}", touId, jsonObject.toString());
//        } else {
//            log.info("发送失败，未找到用户uId={}的session", touId);
//        }
//
//    }
