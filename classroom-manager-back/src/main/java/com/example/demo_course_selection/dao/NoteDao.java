package com.example.demo_course_selection.dao;

import com.example.demo_course_selection.domain.Note;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteDao {


    /**
     * 获取公开的笔记
     * @return
     */
    List<Note> getPublicNotes();

    /**
     * 获取指定用户的笔记
     * @param uId
     * @return
     */
    List<Note> getMyNotes(String uId);

    /**
     * 通过笔记编号获取指定笔记
     * @param nId
     * @return
     */
    Note getNoteById(String nId);

    /**
     * 通过查询条件获取笔记列表
     * @param condition
     * @return
     */
    List<Note> getNotesByCondition(String condition,String isPublic);

    /**
     * 添加笔记
     * @param note
     * @return
     */
    int addNote(Note note);

    /**
     * 更新指定笔记
     * @param note
     * @return
     */
    int updateNote(Note note);

    /**
     * 通过笔记编号删除笔记
     * @param nId
     * @return
     */
    int deleteNote(String nId);


}
