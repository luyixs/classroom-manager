package com.example.demo_course_selection.controller;

import com.example.demo_course_selection.controller.utils.Result;
import com.example.demo_course_selection.domain.Note;
import com.example.demo_course_selection.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping("/list")
    @ResponseBody
    public Result getNotes(@RequestParam(value = "q", required = false, defaultValue = "") String condition,
                           @RequestParam(value = "public", required = false, defaultValue = "true") Boolean isPublic) {
        //defalutValue必须是String类型 框架会自动转换
        List<Note> notes = noteService.getNotesByCondition(condition, isPublic);
        return new Result(true, notes);
    }
    @GetMapping("/detail/{nId}")
    @ResponseBody
    public Result getNoteDetail(@PathVariable("nId") String nId){
//        System.out.println("nID:"+nId);
        Note note = noteService.getNoteById(nId);
        return new Result(true,note);
    }


    @GetMapping("/my")
    @ResponseBody
    public Result getMyNotes(@RequestParam(value = "uId") String uId) {
        System.out.println("UID:"+uId);
        List<Note> notes = noteService.getMyNotes(uId);
        return new Result(true,notes);
    }

    @PostMapping("/push")
    @ResponseBody
    public Result pushNote(@RequestBody Note note) {
        System.out.println("Note: "+note);
        Note target = noteService.getNoteById(note.getNId());
        boolean isSuccess = false;
        //if target!=null run>>update else run>>insert
        isSuccess = target != null ? noteService.updateNote(note) > 0 : noteService.addNote(note) > 0;
        return new Result(isSuccess, note);
    }

    @PostMapping("/upd")
    @ResponseBody
    public Result updateNote(@RequestBody Note note) {
        boolean isSuccess = noteService.updateNote(note) > 0;
        return new Result(isSuccess, note);
    }

    @DeleteMapping("/del")
    @ResponseBody
    public Result deleteNote(@RequestBody String nId) {
        boolean isSuccess = noteService.deleteNote(nId) > 0;
        return new Result(isSuccess, nId);
    }

}
