package com.example.demo_course_selection.service;

import com.example.demo_course_selection.domain.SignIn;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SignInService {

    List<SignIn> getSignInList();

    SignIn getSignInByCode(String siCode);

    List<SignIn> getSignByUID(String uid);


    int addSignIn(SignIn signIn);

    int deleteSignIn(String sicode);

    int updateSignIn(SignIn signIn);
}
