/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Quiz;
import dao.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizDAO extends MyDAO{
    public ArrayList<Quiz> getAllQuiz(){
        ArrayList<Quiz> allQuiz = null;
        
        return allQuiz;
    }
    
    public ArrayList<Quiz> getQuizBySubject(int subjectId){
        ArrayList<Quiz> subjectQuiz = null;
        
        return subjectQuiz;
    } 
    
    public ArrayList<Quiz> getQuizByLesson(int lessonId){
        ArrayList<Quiz> lessonQuiz = null;
        
        return lessonQuiz;
    }
    
    public int editQuiz(int quizId, Quiz quiz){
        int i = 0;
        
        return i;
    }
    
    public int addQuiz(Quiz quiz){
        int i = 0;
        
        return i;
    }
    
    public int deleteQuiz(int quizId){
        int i = 0;
        
        return i;
    }
    
    
}
