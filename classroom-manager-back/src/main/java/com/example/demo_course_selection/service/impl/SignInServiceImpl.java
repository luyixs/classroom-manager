package com.example.demo_course_selection.service.impl;

import com.example.demo_course_selection.dao.SignInDao;
import com.example.demo_course_selection.domain.SignIn;
import com.example.demo_course_selection.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    SignInDao signInDao ;

    @Override
    public List<SignIn> getSignInList() {
        return signInDao.getSignInList();
    }

    @Override
    public SignIn getSignInByCode(String siCode) {
        return signInDao.getSignInByCode(siCode);
    }

    public List<SignIn> getSignByUID(String uid){ return  signInDao.getSignByUID(uid);}



    @Override
    public int addSignIn(SignIn signIn) {
        return signInDao.addSignIn(signIn);
    }

    @Override
    public int deleteSignIn(String sicode) {
        return signInDao.deleteSignIn(sicode);
    }

    @Override
    public int updateSignIn(SignIn signIn) {
        return  signInDao.updateSignIn(signIn);
    }
}
