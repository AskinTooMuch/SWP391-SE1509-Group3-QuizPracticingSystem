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
    public ArrayList<Lesson> getAllLessons();
    
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId);
    
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId);
    
    public Lesson getLessonById(int lessonId);
    
    public int updateLesson(Lesson updatedLesson);
    
    public int deleteLesson(int lessonId);
    
    public int addLesson(Lesson newLesson);
}
