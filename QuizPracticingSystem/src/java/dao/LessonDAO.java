/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  LessonDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.Lesson;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của LessonDAOImpl
 *
 * @author NamDH
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
