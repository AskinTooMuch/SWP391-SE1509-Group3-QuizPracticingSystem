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
 * @author admin
 */
public interface LessonDAO {
    public ArrayList<Lesson> getAllLessons() throws Exception;
    
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId) throws Exception;
    
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId) throws Exception;
    
    public Lesson getLessonById(int lessonId) throws Exception;
    
    public int updateLesson(Lesson updatedLesson) throws Exception;
    
    public int deleteLesson(int lessonId) throws Exception;
    
    public int addLesson(Lesson newLesson) throws Exception;
}
