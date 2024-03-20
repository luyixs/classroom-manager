package com.example.demo_course_selection.interceptor;

import com.example.demo_course_selection.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        User loginUser = (User)request.getSession().getAttribute("loginUser");
//        String uri =request.getRequestURI();
//        if((uri.startsWith("/sc")||uri.startsWith("/lecture"))&&null==loginUser){
//            response.sendRedirect("/login");
//            return false;
//        }
        return true;
    }
}
