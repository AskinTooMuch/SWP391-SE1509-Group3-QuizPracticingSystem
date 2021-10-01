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
    
    public ArrayList<QuizLevel> getAllQuizLevel();
    
    public QuizLevel getQuizLevelById(int quizLevelId);
    
    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel);
    
    public int addQuizLevel(QuizLevel quizLevel);
    
    public int deleteQuizLevel(int quizLevelId);
}