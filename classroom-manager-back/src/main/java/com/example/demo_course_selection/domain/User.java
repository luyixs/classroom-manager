package com.example.demo_course_selection.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户
 *
 * @author zty
 * @date 2022/11/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private String id;
    private String name;
    private String pwd;
    private String className;
    private String academy;
    private String major;
    private String tel;
    private String email;
    private String role;
}
