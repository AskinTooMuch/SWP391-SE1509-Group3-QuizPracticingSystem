/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Quiz;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface QuizINT {
    public ArrayList<Quiz> getAllQuiz();
    
    public Quiz getQuizById(int quizId);
    
    
            
    public ArrayList<Quiz> getQuizBySubject(int subjectId);
    
    public ArrayList<Quiz> getQuizByLesson(int lessonId);
    
    public int editQuiz(int quizId, Quiz quiz);
    
    public int addQuiz(Quiz quiz);
    
    public int deleteQuiz(int quizId);
}
