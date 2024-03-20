package com.example.demo_course_selection;

import cn.hutool.core.date.DateTime;
import com.example.demo_course_selection.domain.SignIn;
import com.example.demo_course_selection.model.SignState;
import com.example.demo_course_selection.service.SignInService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignInTest {

    @Autowired
    SignInService signInService;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public  void state(){
        SignState state = SignState.valueOf("SIGNED");
        System.out.println(state.getState());
        System.out.println(SignState.NOTSIGNED.getState());
    }

    @Test
    public void getList() {
        System.out.println(signInService.getSignInList());
    }

    @Test
    public void getById() {
        System.out.println(signInService.getSignInByCode("0"));
        System.out.println(signInService.getSignByUID("2"));
    }

    @Test
    public void add(){
        System.out.println(signInService.addSignIn(new SignIn("1","1","1","1","1")));
    }
    @Test
    public void del(){
        System.out.println(signInService.deleteSignIn("1"));
    }
    @Test
    public void upd(){
        System.out.println(signInService.updateSignIn(new SignIn("0", "0", "0", "0","1")));
    }

    @Test
    public void testTime() throws ParseException {
        String createTime = "2023/3/27 18:00:29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(createTime);
        Date date2 = new Date();
        System.out.println("创建时间："+date);

        System.out.println("当前时间："+date2);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE,4);
        date = c.getTime();
        System.out.println("创建时间+4分钟："+date);
        System.out.println(date.before(date2));
        System.out.println(date.after(date2));



        System.out.println(date2.getTime()-date.getTime());

    }
    @Test
    public void testRedis(){
        System.out.println(redisTemplate.opsForHash().get("codemap","L4f338"));
    }
}
