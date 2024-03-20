package com.example.demo_course_selection;

import com.example.demo_course_selection.domain.Course;
import com.example.demo_course_selection.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseTest {
    @Autowired
    CourseService courseService;

    @Test
    void getcourseById() {
        System.out.println(courseService.getCourseBycId(""));
        System.out.println(courseService.getCourseByTeacherId("111"));
    }

    @Test
    void getcourseList() {
        System.out.println(courseService.getCourseList());
    }

    @Test
    void addcourse() {
//        Course course = new Course("111", "111", "111", 4,"111", 100, "111","必修","计算机学院","","","123"," ","");
//        System.out.println(courseService.addCourse(course));
    }

    @Test
    void deletecourse() {
        System.out.println(courseService.deleteCourse("1"));
    }

    @Test
    void updatecourse() {
//        Course course = new Course("111", "222", "111", 4,"111", 100, "111","必修","计算机学院","","");
//        System.out.println(courseService.updateCourse(course));
    }
}
