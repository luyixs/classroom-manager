package com.example.demo_course_selection.dao;

import com.example.demo_course_selection.domain.SignIn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SignInDao {

    List<SignIn> getSignInList();

    SignIn getSignInByCode(String siCode);

    List<SignIn> getSignByUID(String uid);

    int addSignIn(SignIn signIn);

    int deleteSignIn(String sicode);

    int updateSignIn(SignIn signIn);



}
