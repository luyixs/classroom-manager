package com.example.demo_course_selection.service.impl;

import com.example.demo_course_selection.dao.SCDao;
import com.example.demo_course_selection.domain.SC;
import com.example.demo_course_selection.domain.SCResult;
import com.example.demo_course_selection.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SCServiceImpl implements SCService {
    @Autowired
    SCDao scDao;

    @Override
    public int addSC(SC sc) {

        return scDao.addSC(sc);
    }

    /**
     * 删除用户为id的所有选课记录
     *
     * @param uId 用户ID
     * @return int
     */
    @Override
    public int deleteSCByuId(String uId) {
        return scDao.deleteSCByuId(uId);
    }

    /**
     * 删除课程号为cid的所有选课记录
     *
     * @param cId 课程ID
     * @return int
     */
    @Override
    public int deleteSCBycId(String cId) {
        return scDao.deleteSCBycId(cId);
    }

    /**
     * 通过scId删除选课记录
     *
     * @param scId 选课号
     * @return int
     */
    @Override
    public int deleteSCByscId(String scId) {
        return scDao.deleteSCByscId(scId);
    }

    /**
     * 更新记录
     *
     * @param sc 选课记录
     * @return int
     */
    @Override
    public int updateSC(SC sc) {
        return scDao.updateSC(sc);
    }

    /**
     * 通过课程编号更新选课记录中的信息
     * @param sc sc
     * @return int
     */
    public int updateSCBycId(SC sc){
        return scDao.updateSCBycId(sc);
    }

    /**
     * 通过用户ID查询选课记录
     *
     * @param uId 用户ID
     * @return {@link SC}
     */
    @Override
    public List<SCResult> getSCByuId(String uId) {
        return scDao.getSCByuId(uId);
    }

    /**
     * 从自己的选课记录中按指定条件查询
     * @param uId       用户ID
     * @param condition 条件
     * @return {@link List}<{@link SCResult}>
     */
    public List<SCResult> getSCByCondition(String uId, String condition){
        return scDao.getSCByCondition(uId,condition);
    }

    /**
     * 通过课程号查询选课记录
     *
     * @param cId 课程号
     * @return {@link SC}
     */
    @Override
    public List<SC> getSCBycId(String cId) {
        return scDao.getSCBycId(cId);
    }

    //查询所有学号姓名分数和课程名的记录
    @Override
    public List<SC> getAllDetailSC() {
        return scDao.getAllDetailSC();
    }

    //获取分数大于某值的记录
}
