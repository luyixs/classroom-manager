package com.example.demo_course_selection.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @JsonProperty("nId")
    private String nId;
    @JsonProperty("uId")
    private String uId;
    private String title;
    private String content;
    private String tag;
    private String editTime;
    private String isPublic;
}
