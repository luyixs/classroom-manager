package com.example.demo_course_selection.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SCResult {
    private String scId;
    @JsonProperty("uId")
    private String uId;
    @JsonProperty("cId")
    private String cId;
    private Integer score;
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
