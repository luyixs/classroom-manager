package com.example.demo_course_selection.config;

import com.example.demo_course_selection.interceptor.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMVCConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/lecture").setViewName("myLecture.html");
    }


    @Autowired
    private MyIntercepter myIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercepter).addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.html"

                );
    }
}
