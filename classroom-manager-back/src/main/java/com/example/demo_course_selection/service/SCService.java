package com.example.demo_course_selection.service;

import com.example.demo_course_selection.domain.SC;
import com.example.demo_course_selection.domain.SCResult;

import java.util.List;

public interface SCService {
    /**
     * 添加选课记录
     * @param sc 选课记录
     * @return int
     */
    int addSC(SC sc);

    /**
     * 删除用户为id的所有选课记录
     * @param uId 用户ID
     * @return int
     */
    int deleteSCByuId(String uId);

    /**
     * 删除课程号为cid的所有选课记录
     * @param cId 课程ID
     * @return int
     */
    int deleteSCBycId(String cId);

    /**
     * 通过id和cId删除选课记录
     *
     * @param scId  选课ID
     * @return int
     */
    int deleteSCByscId(String scId );

    /**
     * 更新记录
     * @param sc 选课记录
     * @return int
     */
    int updateSC(SC sc);

    /**
     * 通过课程编号更新选课记录中的信息
     * @param sc sc
     * @return int
     */
    int updateSCBycId(SC sc);

    /**
     * 通过用户ID查询选课记录
     *
     * @param uId 用户ID
     * @return {@link SC}
     */
    List<SCResult> getSCByuId(String uId);


    /**
     * 从自己的选课记录中按指定条件查询
     * @param uId       用户ID
     * @param condition 条件
     * @return {@link List}<{@link SCResult}>
     */
    List<SCResult> getSCByCondition(String uId,String condition);

    /**
     * 通过课程号查询选课记录
     *
     * @param cid 课程号
     * @return {@link SC}
     */
    List<SC> getSCBycId(String cid);

    //查询所有学号姓名分数和课程名的记录
    List<SC> getAllDetailSC();

    //获取分数大于某值的记录
}
