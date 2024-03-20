package com.example.demo_course_selection.service;

import com.example.demo_course_selection.domain.User;
import org.springframework.stereotype.Service;
import java.util.List;

public interface UserService {
    /**
     * 获取用户列表
     *
     * @return {@link List}<{@link User}>
     */
    List<User> getUserList();

    /**
     * 得到用户id
     *
     * @return {@link User}
     */
    User getUserById(String  id);

    /**
     * 通过id和密码查找用户
     *
     * @param id id
     * @param pwd 密码
     * @return {@link User}
     */
    User getUserByIdAndPwd(String id,String pwd);

    // 查询选择某课程的学生
    List<User> getStudentBycId(String cId);


    /**
     * 获取登录用户
     *
     * @param id   id
     * @param pwd  密码
     * @param role 角色
     * @return {@link User}
     */
    User getLoginUser(String id,String pwd,String role);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return int
     */
    boolean addUser(User user);

    /**
     * 删除用户
     *
     * @param id id
     * @return int
     */
    boolean deleteUser(String id);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return int
     */
    boolean updateUser(User user);

}
