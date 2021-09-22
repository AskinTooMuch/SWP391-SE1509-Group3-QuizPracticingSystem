/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.LessonType;
import dao.LessonTypeINT;
import dao.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author tuan
 */
public class LessonTypeDAO extends MyDAO implements LessonTypeINT{
    @Override
    public ArrayList<LessonType> getAllLessonType(){
        return null;
    }
    
    @Override
    public LessonType getLessonTypeById(int ltId){
        return null;
    }
    
    @Override
    public int addLessonType(LessonType newLessonType){
        return 0;
    }
    
    @Override
    public int updateLessonType(LessonType updatedLessonType){
        return 0;
    }
    
    @Override
    public int deleteLessonType(int ltId){
        return 0;
    }
}
