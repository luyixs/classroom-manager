package com.example.demo_course_selection.dto;

import com.example.demo_course_selection.model.SignState;

public interface SignInMessage {
    //签到码
     String code = null;
    //状态
     SignState state = SignState.NOTSIGNED;
}
