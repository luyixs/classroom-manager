package com.example.demo_course_selection.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生选课
 *
 * @author zty
 * @date 2022/11/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SC {
    private String scId;
    @JsonProperty("uId")
    private String uId;
    @JsonProperty("cId")
    private String cId;
    private Integer score;
}
