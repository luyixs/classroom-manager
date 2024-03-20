package com.example.demo_course_selection.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {
    @JsonProperty("siCode")
    private String siCode;
    @JsonProperty("cId")
    private String cId;
    private String uId;
    private String createTime;
    private String expire;
}
