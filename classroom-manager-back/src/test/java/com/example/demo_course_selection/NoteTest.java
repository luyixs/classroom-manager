package com.example.demo_course_selection;

import com.example.demo_course_selection.dao.NoteDao;
import com.example.demo_course_selection.domain.Note;
import com.example.demo_course_selection.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest {
    @Autowired
    NoteService noteService;

    @Test
    void get1() {
//        System.out.println(noteService.getPublicNotes());
//        System.out.println(noteService.getMyNotes("00"));
//        System.out.println(noteService.getNoteById("00"));
        System.out.println(noteService.getNotesByCondition("",true));
    }

    @Test
    void add() {
        Note n = new Note("000", "00", "我是标题", "我是content", "", "16:28", "false");
        System.out.println(noteService.addNote(n));
    }
    @Test
    void upd(){
        Note n = new Note("00", "00", "我是标题", "我是内容", "测试", "16:28", "true");
        System.out.println(noteService.updateNote(n));
    }

    @Test
    void del(){
        noteService.deleteNote("00");

    }
}
