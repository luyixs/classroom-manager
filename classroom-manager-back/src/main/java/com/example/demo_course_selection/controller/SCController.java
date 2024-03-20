package com.example.demo_course_selection.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo_course_selection.controller.utils.Result;
import com.example.demo_course_selection.domain.Course;
import com.example.demo_course_selection.domain.SC;
import com.example.demo_course_selection.domain.SCResult;
import com.example.demo_course_selection.domain.User;
import com.example.demo_course_selection.service.CourseService;
import com.example.demo_course_selection.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 选课记录控制器
 * 1.跳转页面
 * 2.筛选课程列表（查询所有选课、通过搜索关键词查询所有选课、查询登录用户的选课、通过搜索关键词查询登录用户的选课）
 * 3.退课
 * 4.分页
 * @author zty
 * @date 2022/11/16
 */
@Controller
@RequestMapping("/sc")
public class SCController {

    @Autowired
    CourseService courseService;
    @Autowired
    SCService scService;


    @GetMapping()
    public String toSC() {
        return "sc";
    }

    @GetMapping("/mycourse")
    public String toMyCourse() {
        return "myCourse";
    }

    /**
     * 获得课程列表(q='')
     * 通过指定条件获得课程列表(q!='')
     * @param condition 条件
     * @return {@link Result}
     */
    @GetMapping("/list")
    @ResponseBody
    public Result getCourseList(@RequestParam(value = "q", required = false, defaultValue = "") String condition, HttpSession session) {
        System.out.println("Condition:" + condition);
        User student = (User) session.getAttribute("loginUser");
        List<Course> list;
        if (condition.equals("")) list = courseService.getCourseList();
        else {
            list = courseService.getCourseByCondition(condition);
        }
        System.out.println("List Size:" + list.size());
        return new Result(true, list);
    }


    /**
     * 查询我所选的课程列表(q='')
     * 从自己的选课记录中按指定条件查询 (q!='')
     * @param session 会话
     * @return {@link Result}
     */
    @GetMapping("/mycourse/list")
    @ResponseBody
    public Result getSCByuId(HttpSession session, @RequestParam(value = "q", required = false, defaultValue = "") String condition) {
        System.out.println("Condition:" + condition);
        User student = (User) session.getAttribute("loginUser");
        List<SCResult> list;
        if(condition.equals("")) list = scService.getSCByuId(student.getId());
        else {
            list = scService.getSCByCondition(student.getId(),condition);
        }
        System.out.println("MyCourse List:" + list.size());
        return new Result(true, list);
    }


    /**
     * 添加选课记录
     *
     * @param sc 选课记录
     * @return {@link Result}
     */
    @PostMapping
    @ResponseBody
    public Result addSC(@RequestBody SC sc) {
        System.out.println("SC:" + sc);
        boolean isSuccess = scService.addSC(sc) > 0;
        return new Result(isSuccess, sc);
    }

    /**
     * 删除选课记录
     *
     * @param scId sc id
     * @return {@link Result}
     */
    @DeleteMapping
    @ResponseBody
    public Result dropSC(@RequestBody String scId) {
        System.out.println("DELETE SCID:" + scId);
        boolean isSuccess = scService.deleteSCByscId(scId) > 0;
        return new Result(isSuccess, null, null);
    }

    /**
     * 获取分页
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @return {@link Result}
     */
    @GetMapping("{currentPage}/{pageSize}")
    @ResponseBody
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
//        return new Result(true,scService.getPage(currentPage,pageSize));
        return new Result();
    }
}
