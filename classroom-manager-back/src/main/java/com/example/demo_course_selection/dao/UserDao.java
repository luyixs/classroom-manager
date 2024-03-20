package com.example.demo_course_selection.dao;

import com.example.demo_course_selection.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository  //将UserDao交给spring 容器馆里
public interface UserDao {

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
    User getUserById(String id);


    /**
     * 通过id和密码查找用户
     *
     * @param id  id
     * @param pwd 密码
     * @return {@link User}
     */
    User getUserByIdAndPwd(String id, String pwd);

    /**
     * 通过id和密码查找用户
     *
     * @param id  id
     * @param pwd 密码
     * @return {@link User}
     */
    User getLoginUser(String id, String pwd, String role);

    // 查询选择某课程的学生
    List<User> getStudentBycId(String cId);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return int
     */
    int addUser(User user);

    /**
     * 删除用户
     *
     * @param id id
     * @return int
     */
    int deleteUser(String id);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return int
     */
    int updateUser(User user);


}
