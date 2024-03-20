package com.example.demo_course_selection.controller;

import com.example.demo_course_selection.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @GetMapping("/test")
    public User test() {
        return new User("123","123","123","123","123","123","123","123","123");
    }

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/session/loginUser")
    @ResponseBody
    public User getSession(HttpSession session){
        return (User)session.getAttribute("loginUser")==null?null: (User)session.getAttribute("loginUser");
    }



}
