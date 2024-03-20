package com.example.demo_course_selection.controller.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 定义返回结果类
 * @author zty
 * @date 2022/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    boolean isSuccess=false;  //是否操作成功
    Object data;    //返回数据对象
    String msg;     //返回消息

    public Result(boolean isSuccess, Object data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public Result(Object data){
        this.data = data;
    }

}

/**
 * 前端数据返回格式示例：
 * {
 *     "data": {
 *         "id": "1",
 *         "name": "1",
 *         "pwd": "1",
 *         "className": "2022级软件工程1班",
 *         "academy": "计算机学院",
 *         "major": "软件工程",
 *         "tel": "1",
 *         "email": "1",
 *         "role": "student"
 *     },
 *     "msg": null,
 *     "success": true
 * }
 */
