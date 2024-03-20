package com.example.demo_course_selection;

import com.example.demo_course_selection.dao.UserDao;
import com.example.demo_course_selection.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoCourseSelectionApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void getUserById() {
        System.out.println(userDao.getUserById(""));
    }

    @Test
    void getUserList() {
        System.out.println(userDao.getUserList());
    }

    @Test
    void addUser() {
        User user = new User("111", "111", "111", "111", "111", "111", "111", "111", "111");
        System.out.println(userDao.addUser(user));
    }

    @Test
    void deleteUser() {
        System.out.println(userDao.deleteUser("111"));
    }

    @Test
    void updateUser() {
        User user = new User("111", "222", "111", "111", "111", "111", "111", "111", "111");
        System.out.println(userDao.updateUser(user));
    }

}
