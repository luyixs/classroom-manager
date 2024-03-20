package com.example.demo_course_selection;

import com.example.demo_course_selection.dao.SCDao;
import com.example.demo_course_selection.domain.SC;
import com.example.demo_course_selection.service.NoteService;
import com.example.demo_course_selection.service.SCService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
public class SCTest {
    @Autowired
    SCService scService;
    @Autowired
    NoteService noteService;

    @Test
    void addSC(){
        SC sc = new SC("202202c3","202202","c3",88);
        System.out.println(scService.addSC(sc));
    }

    @Test
    void deleteSCByuId(){
        System.out.println(scService.deleteSCByuId("202202"));
    }

    @Test
    void deleteSCBycId()    {
        System.out.println(scService.deleteSCBycId("c3"));
    }

    @Test
    void deleteSCByscId(){
        System.out.println(scService.deleteSCByscId("202202c3"));
    }

    @Test
    void updateSC(){
        SC sc = new SC("202202c3","202202","c1",88);
        System.out.println(scService.updateSC(sc));
    }

    @Test
    void getSCByuId(){
        System.out.println(scService.getSCByuId("202202"));
    }

    @Test
    void getSCBycId(){
        System.out.println(scService.getSCBycId("c1"));
    }

    @Test
    void getAllDetailSC(){
        List<SC> scList = scService.getAllDetailSC();
        Iterator<SC> iterator = scList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
