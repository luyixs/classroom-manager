package com.example.demo_course_selection.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo_course_selection.controller.utils.Result;
import com.example.demo_course_selection.domain.User;
import com.example.demo_course_selection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.logging.Logger;


/**
 * 登录控制器
 * @author zty
 * @date 2022/11/16
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    /**
     * 注册
     *
     * @param map 获取注册请求参数
     * @return {@link Result}
     */
    @PostMapping("/reg")
    @ResponseBody
    public Result register(@RequestBody Map<String, Object> map) {
        System.out.println(map.get("user"));
        Result r = new Result();
        /**
         * User user = (User) map.get("user");
         * [ERROR] java.util.LinkedHashMap cannot be cast to com.example.demo_course_selection.domain.User
         * 需要将map.get("user")中的值转换为json字符串 然后将json转换为User对象
         */
        User user = JSONObject.parseObject(JSON.toJSONString(map.get("user")), User.class);

        try {
            r.setSuccess(userService.addUser(user));
            r.setData(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

    /**
     * 登录
     * @param u 获取登录请求参数
     * @return {@link Result}
     */
    @PostMapping("/")
    @ResponseBody
    public Result login(@RequestBody User u, HttpSession session) {
//        System.out.println(u);
        User loginUser = userService.getLoginUser(u.getId(), u.getPwd(), u.getRole());
        System.out.println(loginUser);
        boolean isSuccess = null != loginUser;
        if (isSuccess) session.setAttribute("loginUser", loginUser);
        return new Result(isSuccess, loginUser);
    }

    @GetMapping("/out")
    public String logOut(HttpSession session){
        session.invalidate();
        return "index";
    }

}
