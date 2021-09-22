/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.util.ArrayList;
import bean.QuizLevel;
import dao.MyDAO;
import dao.QuizLevelINT;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizLevelDAO extends MyDAO implements QuizLevelINT{
    
    @Override
    public ArrayList<QuizLevel> getAllQuizLevel(){
        ArrayList<QuizLevel> quizLevels = null;
        return quizLevels;
    }
    
    @Override
    public QuizLevel getQuizLevelById(int quizLevelId){
        QuizLevel quizLevel = null;
        return quizLevel;
    }
    
    @Override
    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel){
        int i = 0;
        
        return i;
    }
    
    @Override
    public int addQuizLevel(QuizLevel quizLevel){
        int i = 0;
        
        return i;
    }
    
    @Override
    public int deleteQuizLevel(int quizLevelId){
        int i = 0;
        
        return i;
    }
}