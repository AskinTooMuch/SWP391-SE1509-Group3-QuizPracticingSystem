/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Lesson;
import java.util.ArrayList;

/**
 *
 * @author tuan
 */
public class LessonDAO extends MyDAO{
    public ArrayList<Lesson> getAllLessons(){
        return null;
    }
    
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId){
        return null;
    }
    
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId){
        return null;
    }
    
    public Lesson getLessonById(int lessonId){
        return null;
    }
    
    public int updateLesson(Lesson updatedLesson){
        return 0;
    }
    
    public int deleteLesson(int lessonId){
        return 0;
    }
    
    public int addLesson(Lesson newLesson){
        return 0;
    }
}
