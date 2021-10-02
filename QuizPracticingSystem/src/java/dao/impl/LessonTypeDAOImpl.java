/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.LessonType;
import dao.DBConnection;
import java.util.ArrayList;
import dao.LessonTypeDAO;

/**
 *
 * @author tuan
 */
public class LessonTypeDAOImpl extends DBConnection implements LessonTypeDAO{
    @Override
    public ArrayList<LessonType> getAllLessonType() throws Exception{
        return null;
    }
    
    @Override
    public LessonType getLessonTypeById(int ltId) throws Exception{
        return null;
    }
    
    @Override
    public int addLessonType(LessonType newLessonType) throws Exception{
        return 0;
    }
    
    @Override
    public int updateLessonType(LessonType updatedLessonType) throws Exception{
        return 0;
    }
    
    @Override
    public int deleteLessonType(int ltId) throws Exception{
        return 0;
    }
}
