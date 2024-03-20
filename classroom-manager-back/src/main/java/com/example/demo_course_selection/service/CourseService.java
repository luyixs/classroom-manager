package com.example.demo_course_selection.service;

import com.example.demo_course_selection.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService  {

    /**
     * 获得课程列表
     *
     * @return {@link List}<{@link Course}>
     */
    List<Course> getCourseList();

    /**
     * 通过cid获取课程
     *
     * @param cId cid
     * @return {@link Course}
     */
    Course getCourseBycId(String cId);

    /**
     * 通过teacherId 获取课程
     *
     * @param teacherId 老师id
     * @return {@link Course}
     */
    List<Course> getCourseByTeacherId(String teacherId);

    /**
     * 添加课程
     *
     * @param course 课程
     * @return int
     */
    int addCourse(Course course);

    /**
     * 删除课程
     *
     * @param cId 课程号
     * @return int
     */
    int deleteCourse(String cId);

    /**
     * 更新课程
     *
     * @param course 课程
     * @return int
     */
    int updateCourse(Course course);

    List<Course> getCourseByCondition(String condition);

    /**
     * 通过教师ID和查询条件获取授课
     *
     * @param teacherId   老师ID
     * @param condition 条件
     * @return {@link List}<{@link Course}>
     */
    List<Course> getTeacherCourseByCondition(String teacherId,String condition);

}
