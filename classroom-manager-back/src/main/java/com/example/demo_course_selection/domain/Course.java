package com.example.demo_course_selection.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程
 *
 * @author zty
 * @date 2022/11/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @JsonProperty("cId")
    private String cId;     //[lombok BUG] 前后端字段名一致 但后端接受值为null https://blog.csdn.net/weixin_43286071/article/details/106501824
//    private String courseId;
    @JsonProperty("cName")
    private String cName;
    private String teacherId;
    private Integer credit;
    private String classRoom;
    private Integer limit;
    private String timeRange;
    private String type;
    private String academy;
    @JsonProperty("cIntro")
    private String cIntro;


}
