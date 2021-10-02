/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.QuizLevel;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface QuizLevelDAO {
    
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception;
    
    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception;
    
    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel) throws Exception;
    
    public int addQuizLevel(QuizLevel quizLevel) throws Exception;
    
    public int deleteQuizLevel(int quizLevelId) throws Exception;
}