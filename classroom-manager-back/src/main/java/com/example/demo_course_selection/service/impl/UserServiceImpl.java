package com.example.demo_course_selection.service.impl;

import com.example.demo_course_selection.dao.UserDao;
import com.example.demo_course_selection.domain.User;
import com.example.demo_course_selection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getStudentBycId(String cId) {
        return userDao.getStudentBycId(cId);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByIdAndPwd(String id,String pwd){
        return userDao.getUserByIdAndPwd(id,pwd);
    }

    @Override
    public User getLoginUser(String id, String pwd, String role) {
        return userDao.getLoginUser(id,pwd,role);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user)>0;
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.deleteUser(id)>0;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user)>0;
    }
}
