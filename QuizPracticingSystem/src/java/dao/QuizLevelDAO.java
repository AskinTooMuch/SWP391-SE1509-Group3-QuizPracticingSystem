/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import bean.QuizLevel;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizLevelDAO extends MyDAO{
    
    public ArrayList<QuizLevel> getAllQuizLevel(){
        ArrayList<QuizLevel> quizLevels = null;
        return quizLevels;
    }
    
    public QuizLevel getQuizLevelById(int quizLevelId){
        QuizLevel quizLevel = null;
        return quizLevel;
    }
    
    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel){
        int i = 0;
        
        return i;
    }
    
    public int addQuizLevel(QuizLevel quizLevel){
        int i = 0;
        
        return i;
    }
    
    public int deleteQuizLevel(int quizLevelId){
        int i = 0;
        
        return i;
    }
}
