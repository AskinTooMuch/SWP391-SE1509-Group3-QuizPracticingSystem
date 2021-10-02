/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Lesson;
import java.util.ArrayList;
import dao.MyDAO;
import dao.LessonDAO;

/**
 *
 * @author tuan
 */
public class LessonDAOImpl extends MyDAO implements LessonDAO{
    @Override
    public ArrayList<Lesson> getAllLessons() throws Exception{
        return null;
    }
    
    @Override
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId) throws Exception{
        return null;
    }
    
    @Override
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId) throws Exception{
        return null;
    }
    
    @Override
    public Lesson getLessonById(int lessonId) throws Exception{
        return null;
    }
    
    @Override
    public int updateLesson(Lesson updatedLesson) throws Exception{
        return 0;
    }
    
    @Override
    public int deleteLesson(int lessonId) throws Exception{
        return 0;
    }
    
    @Override
    public int addLesson(Lesson newLesson) throws Exception{
        return 0;
    }
}
