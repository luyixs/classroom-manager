package com.example.demo_course_selection.dao;

import com.example.demo_course_selection.domain.SC;
import com.example.demo_course_selection.domain.SCResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SCDao {

    /**
     * 添加选课记录
     * @param sc 选课记录
     * @return int
     */
    int addSC(SC sc);

    /**
     * 删除用户为uid的所有选课记录
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
     * 通过scId删除选课记录
     * @param scId 选课ID
     * @return int
     */
    int deleteSCByscId(String scId);

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


    // 注：此处不应返回SC 因为还携带有其他列 暂定返回为SC
    //查询所有学号姓名分数和课程名的记录
    List<SC> getAllDetailSC();

    //获取分数大于某值的记录



}




/**
 * 注：删除用户或课程时，要先删除选课表中对应的选课记录，再删除用户或者课程
 *
 * INSERT INTO t_sc VALUES('202203','c1' ,90);          --添加选课记录 因为是外键 所以添加之前用户表得有‘202203’的数据 课程有‘c1’的数据，
 * DELETE FROM t_sc WHERE id = '202203';                       --删除选课记录
 * UPDATE t_sc SET score=100 WHERE id='202203' and cid='c1';   --更新学生课程成绩
 * SELECT * FROM t_sc WHERE id = '202201';              --查询用户为202201的选课记录
 * SELECT * FROM t_course,t_sc WHERE t_sc.cid= t_course.cid and t_sc.uid = '2022010101'; 查询用户为'2022010101'的选课课程记录
 *
 * SELECT * FROM t_sc WHERE cid = 'c1';                 --查询课程号为c1的选课记录
 * SELECT * FROM t_sc WHERE score >=80;                 --查询分数大于80的选课记录
 * SELECT * FROM t_sc,t_course WHERE t_sc.cid=t_course.cid AND t_course.teacher_id='222'; --查询学生选课教师号为'222'的记录
 * SELECT u.id,u.`name`,sc.score,c.c_name FROM t_sc sc ,t_user u,t_course c
 * WHERE u.id=sc.id AND sc.cid=c.cid;                   --查询学号名字分数和课程名
 *
 *
 */