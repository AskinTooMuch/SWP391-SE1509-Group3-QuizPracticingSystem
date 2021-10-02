/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.LessonType;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface LessonTypeDAO {
    
    public ArrayList<LessonType> getAllLessonType() throws Exception;
    
    public LessonType getLessonTypeById(int ltId) throws Exception;
    
    public int addLessonType(LessonType newLessonType) throws Exception;
    
    public int updateLessonType(LessonType updatedLessonType) throws Exception;
    
    public int deleteLessonType(int ltId) throws Exception;
    
}
