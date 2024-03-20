package com.example.demo_course_selection.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo_course_selection.components.SignInServer;
import com.example.demo_course_selection.controller.utils.Result;
import com.example.demo_course_selection.domain.SignIn;
import com.example.demo_course_selection.domain.User;
import com.example.demo_course_selection.domain.UserSignState;
import com.example.demo_course_selection.service.SignInService;
import com.example.demo_course_selection.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 签到控制器
 *
 * @author zty
 * @date 2023/03/08
 */
@Controller
@RequestMapping("/signin")
public class SignInController {

    @Autowired
    SignInService signInService;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(SignInController.class);

    private static final String codeMapKey = "codemap";


    /**
     * 创建签到
     *
     * @param map     map
     * @param session 会话
     * @return {@link Result}
     */
    @PostMapping("/create")
    @ResponseBody
    public Result createSignIn(@RequestBody Map<String, Object> map, HttpSession session) {
        //{signInData={siCode=4qjETt,  cId=thPyx9, uId=xxx createTime=2023/3/13 23:42:43, expire=1}}

        //向数据库添加一条签到信息
        //obj >> String >> SignIn
        SignIn signIn = JSONUtil.toBean(JSON.toJSONString(map.get("signInData")), SignIn.class);
        System.out.println(signIn); //SignIn(siCode=4qjETt, cId=thPyx9, createTime=2023/3/13 23:42:43, expire=1)
        boolean isSuccess = signInService.addSignIn(signIn) > 0; //添加到数据库是否成功
//        System.out.println(isSuccess);

        //获取该课程的学生列表
        List<User> students = userService.getStudentBycId(signIn.getCId());
        Map<String, String> stateMap = new HashMap<>();
        students.forEach(s -> { //将用户类向下转型变为学生签到状态（添加签到码和签到状态）列表添加至map中
            System.out.println("toJSONString: " + JSONUtil.toJsonStr(new UserSignState(s)));
            stateMap.put(s.getId(), JSONUtil.toJsonStr(new UserSignState(s)));
        });

        /**
         * {
         * "state":"NOTSIGNED",
         * "user":{"academy":"计算机学院","className":"2022级软件工程1班","email":"zs@null.com","id":"2022010101","major":"软件工程","name":"张三","pwd":"zs123","role":"student","tel":"11111111111"}
         * }
         */

        //向Redis中添加选择该课程的学生签到状态
        String signHashKey = "code:" + signIn.getSiCode(); //code:xxx
        //将map添加至Redis Hash结构中 code:xxx id {user:...,code:null,SignState:SignState.NOTSIGNED}
        redisTemplate.opsForHash().putAll(signHashKey, stateMap);
        redisTemplate.expire(signHashKey, Long.parseLong(signIn.getExpire()), TimeUnit.MINUTES);

        //  将签到码和教师ID关联起来以便后续向教师端转发信息使用
//        String signStringKey = signIn.getSiCode();
//        redisTemplate.opsForValue().set(signStringKey, signIn.getUId());
//        redisTemplate.expire(signStringKey, Long.parseLong(signIn.getExpire()), TimeUnit.MINUTES);
        redisTemplate.opsForHash().put(codeMapKey, signIn.getSiCode(), signIn);
        log.info("{}发起签到，签到码为：{}", signIn.getUId(), signIn.getSiCode());

        return new Result(isSuccess, new JSONObject().set("userSignStates", redisTemplate.opsForHash().multiGet(signHashKey, redisTemplate.opsForHash().keys(signHashKey))));
    }

    @PostMapping("/signed")
    @ResponseBody
    public Result signedIn(@RequestBody UserSignState userSignState) {
        System.out.println("userSignState: " + userSignState);

        String hashKey = "code:" + userSignState.getCode();
        //判断签到码是否存在 若已存在判断是否已签
        if (redisTemplate.opsForHash().hasKey(hashKey, userSignState.getUser().getId())) {
            //更新Redis缓存中的学生签到状态信息
            redisTemplate.opsForHash().put(hashKey, userSignState.getUser().getId(), JSONUtil.toJsonStr(userSignState));
            log.info("{}签到成功，签到码为：{}", userSignState.getUser().getId(), userSignState.getCode());

            sendMessageToTeacher(userSignState.getCode());

            return new Result(true, userSignState);
        }


//        redisTemplate.opsForHash().

        //解决方案一
        //在create接口中存储teacher id(从session中获取) ，将教师ID和签到码关联起来（使用map）,
        //通过签到码查询教师id
        //遍历SignInServer中的sessionMap 若sessionmap中的key与教师id相等，则向该key对应的session发送信息

        //解决方案二
        //在Redis中key中将签到码与教师id关联起来，

        //解决方案三：
        //在数据库中添加教师编号列


        //若某一学生发送一条签到信息，则将Redis中的学生签到状态改为已签，
        //获取Redis中该签到码对应的的学生签到状态信息
        //向选择该课程的学生客户端发送 选择该课程学生的签到状态
        // 若该学生处于该签到码的待签到列表中则向该客户端发送消息
        // for(student in signinstudents){
        // if(uId.equals().)
        // }
        //


        return new Result();
    }

    @GetMapping("/state")
    @ResponseBody
    public Result getStudentState(String uid) throws ParseException {
        System.out.println(uid);
        List<SignIn> signInList = signInService.getSignByUID(uid);
        //判断有没有记录
        if(signInList.size()<=0){
            return new Result(false,null,"没有正在进行的签到");
        }

        //若有记录，则获取第一条记录判断是否正在进行中
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date time = sdf.parse(signInList.get(0).getCreateTime());
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MINUTE,Integer.parseInt(signInList.get(0).getExpire()));
        time = c.getTime(); //最新记录的签到截至时间 endtime
        Date now = new Date();
        //如果截至时间在当前时间之前，则说明已过期
        if(time.before(now)){
            return new Result(false,null,"没有正在进行的签到");
        }

        String siCode = signInList.get(0).getSiCode();
        if (redisTemplate.hasKey("code:" + siCode)) {
            sendMessageToTeacher(siCode);
        }
        return new Result(true,signInList.get(0),"有正在进行的签到");
    }


    /**
     * 向教师端发送学生签到状态信息
     *
     * @param code 签到码
     */
    public void sendMessageToTeacher(String code) {
        String hashKey = "code:" + code;
        SignIn signIn = (SignIn) redisTemplate.opsForHash().get(codeMapKey, code); //从Redis中获取发起签到者（教师端）
        String teacherId = signIn.getUId();
        SignInServer signInServer = new SignInServer();
        Session toSession = SignInServer.sessionMap.get(teacherId);     //获取teacherId 对应的session
        List<UserSignState> userSignStates = redisTemplate.opsForHash().multiGet(hashKey, redisTemplate.opsForHash().keys(hashKey));
        //需要格式：{ "userSignStates":[ {"user":"{...}","code":"xxx","state":"已签"} ]}
        signInServer.sendMessage(new JSONObject().set("userSignStates", userSignStates).toString(), toSession);
    }


}

/**
 * 在Redis中将签到码与教师id关联起来
 * <p>
 * 半双工通信模式： 客户端请求 > 服务器响应
 * <p>
 * 缺点：
 * 要实现实时推送学生的签到状态需要 客户端不断发送请求返回数据，而服务器不能主动向客户端推送信息，会导致服务器频繁请求，加大服务器的压力，浪费网络带宽资源
 * 解决方案：
 * 使用WebSocket技术实现双工道通信 服务器主动向客户端实时发送数据
 * <p>
 * 半双工通信模式： 客户端请求 > 服务器响应
 * <p>
 * 缺点：
 * 要实现实时推送学生的签到状态需要 客户端不断发送请求返回数据，而服务器不能主动向客户端推送信息，会导致服务器频繁请求，加大服务器的压力，浪费网络带宽资源
 * 解决方案：
 * 使用WebSocket技术实现双工道通信 服务器主动向客户端实时发送数据
 * <p>
 * 半双工通信模式： 客户端请求 > 服务器响应
 * <p>
 * 缺点：
 * 要实现实时推送学生的签到状态需要 客户端不断发送请求返回数据，而服务器不能主动向客户端推送信息，会导致服务器频繁请求，加大服务器的压力，浪费网络带宽资源
 * 解决方案：
 * 使用WebSocket技术实现双工道通信 服务器主动向客户端实时发送数据
 */

/**
 * 半双工通信模式： 客户端请求 > 服务器响应
 * <p>
 * 缺点：
 * 要实现实时推送学生的签到状态需要 客户端不断发送请求返回数据，而服务器不能主动向客户端推送信息，会导致服务器频繁请求，加大服务器的压力，浪费网络带宽资源
 * 解决方案：
 * 使用WebSocket技术实现双工道通信 服务器主动向客户端实时发送数据
 */
