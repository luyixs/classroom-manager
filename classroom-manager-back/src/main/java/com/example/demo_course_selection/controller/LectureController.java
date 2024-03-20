package com.example.demo_course_selection.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo_course_selection.controller.utils.Result;
import com.example.demo_course_selection.domain.Course;
import com.example.demo_course_selection.domain.SC;
import com.example.demo_course_selection.domain.User;
import com.example.demo_course_selection.service.CourseService;
import com.example.demo_course_selection.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 教师授课控制器
 * 1.跳转页面
 * 2.获取授课列表
 * 3.添加授课
 * 4.编辑授课
 * 5.删除授课
 *
 * @author zty
 * @date 2022/11/16
 */
@Controller
@RequestMapping("/lecture")
public class LectureController {
    @Autowired
    CourseService courseService;
    @Autowired
    SCService scService;

    @GetMapping()
    public String toLecture() {
        return "myLecture";
    }

    /**
     * 通过查询条件获得课程列表
     *
     * @param condition 条件
     * @param session   会话
     * @return {@link Result}
     */
    @GetMapping("/list")
    @ResponseBody
    public Result getLectureList(@RequestParam(value = "q", required = false, defaultValue = "") String condition, HttpSession session) {
//        System.out.println("Condition:" + condition);
        User teacher = (User) session.getAttribute("loginUser");
        System.out.println(teacher);
        List<Course> list;
        if (condition.equals("")) {
            list = courseService.getCourseByTeacherId(teacher.getId());
        } else {
            list = courseService.getTeacherCourseByCondition(teacher.getId(), condition);
        }
        System.out.println("List Size:" + list.size());
        return new Result(true, list);
    }

    /**
     * 添加授课
     *
     * @param map 获取全部请求参数
     * @return {@link Result}
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addLecture(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Course course = JSONObject.parseObject(JSON.toJSONString(map.get("lecture")), Course.class);
        System.out.println(course);
        boolean isSuccess = courseService.addCourse(course) > 0;
        return new Result(isSuccess, course);
    }

    /**
     * 通过课程号删除该课程（并且删除该课程的选课记录）
     *
     * @param cId 课程号
     * @return
     */
    @DeleteMapping("/del")
    @ResponseBody
    public Result deleteLecture(@RequestParam(value = "cId", required = true, defaultValue = "") String cId) {
        boolean flag1, flag2 = false;
        //1.删除选课记录
        System.out.println("删除选课记录：" + (scService.deleteSCBycId(cId) > 0));
        //2.删除课程
        System.out.println("删除课程：" + (courseService.deleteCourse(cId) > 0));
        return new Result(flag2, cId);
    }

    /**
     * 通过课程号更新课程信息
     *
     * @param course 课程
     * @return {@link Result}
     */
    @PostMapping("/upd")
    @ResponseBody
    public Result updateLecture(@RequestBody Course course) {
        System.out.println(course);
        //1.更新课程表中的信息
        boolean flag = courseService.updateCourse(course) > 0;
        return new Result(flag,course);
    }

}
