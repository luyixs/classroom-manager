package com.example.demo_course_selection.domain;

import com.example.demo_course_selection.dto.SignInMessage;
import com.example.demo_course_selection.model.SignState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignState implements SignInMessage {
    private User user;
    //签到码
    private String code = null;
    //签到状态 默认未签
    private String state = SignState.NOTSIGNED.getState();

    public UserSignState(User user) {
        this.user = user;
    }
}
