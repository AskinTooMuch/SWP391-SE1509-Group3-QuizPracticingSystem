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
public interface QuizDAO {
    public ArrayList<Quiz> getAllQuiz() throws Exception;
    
    public Quiz getQuizById(int quizId) throws Exception;
    
    public Quiz getQuizByQuizTakeId(int quizTakeId) throws Exception;
            
    public ArrayList<Quiz> getQuizBySubject(int subjectId) throws Exception;
    
    public ArrayList<Quiz> getQuizByLesson(int lessonId) throws Exception;
    
    public int editQuiz(int quizId, Quiz quiz) throws Exception;
    
    public int addQuiz(Quiz quiz) throws Exception;
    
    public int deleteQuiz(int quizId) throws Exception;
}
