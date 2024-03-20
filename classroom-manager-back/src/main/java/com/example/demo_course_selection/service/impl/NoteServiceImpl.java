package com.example.demo_course_selection.service.impl;

import com.example.demo_course_selection.dao.NoteDao;
import com.example.demo_course_selection.domain.Note;
import com.example.demo_course_selection.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteDao noteDao;

    @Override
    public List<Note> getPublicNotes() {
        return noteDao.getPublicNotes();
    }

    @Override
    public List<Note> getMyNotes(String uId) {
        return noteDao.getMyNotes(uId);
    }

    @Override
    public Note getNoteById(String nId) {
        return noteDao.getNoteById(nId);
    }

    @Override
    public List<Note> getNotesByCondition(String condition,Boolean isPublic) {
        return noteDao.getNotesByCondition(condition,String.valueOf(isPublic));
    }

    @Override
    public int addNote(Note note) {
        return noteDao.addNote(note);
    }

    @Override
    public int updateNote(Note note) {
        return noteDao.updateNote(note);
    }

    @Override
    public int deleteNote(String nId) {
        return noteDao.deleteNote(nId);
    }




}
