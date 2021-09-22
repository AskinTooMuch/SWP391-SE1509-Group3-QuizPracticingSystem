/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Lesson;
import dao.LessonINT;
import java.util.ArrayList;
import dao.MyDAO;

/**
 *
 * @author tuan
 */
public class LessonDAO extends MyDAO implements LessonINT{
    @Override
    public ArrayList<Lesson> getAllLessons(){
        return null;
    }
    
    @Override
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId){
        return null;
    }
    
    @Override
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId){
        return null;
    }
    
    @Override
    public Lesson getLessonById(int lessonId){
        return null;
    }
    
    @Override
    public int updateLesson(Lesson updatedLesson){
        return 0;
    }
    
    @Override
    public int deleteLesson(int lessonId){
        return 0;
    }
    
    @Override
    public int addLesson(Lesson newLesson){
        return 0;
    }
}
