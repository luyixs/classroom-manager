package com.example.demo_course_selection.service.impl;

import com.example.demo_course_selection.dao.CourseDao;
import com.example.demo_course_selection.domain.Course;
import com.example.demo_course_selection.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Override
    public List<Course> getCourseList() {
        return courseDao.getCourseList();
    }

    @Override
    public Course getCourseBycId(String cId) {
        return courseDao.getCourseBycId(cId);
    }

    @Override
    public List<Course> getCourseByTeacherId(String teacherId) {
        return courseDao.getCourseByTeacherId(teacherId);
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public int deleteCourse(String cId) {
        return courseDao.deleteCourse(cId);
    }

    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public List<Course> getCourseByCondition(String condition) {
        return courseDao.getCourseByCondition(condition);
    }

    @Override
    public List<Course> getTeacherCourseByCondition(String teacherId, String condition) {
        return courseDao.getTeacherCourseByCondition(teacherId,condition);
    }
}
