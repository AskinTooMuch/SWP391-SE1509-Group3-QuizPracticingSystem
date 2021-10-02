/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Question;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public interface QuizQuizHandleDAO {

    public QuizQuizHandle generateQuiz(ArrayList<Question> questionList,int quizId) throws Exception;

    public double calculateScore(QuizQuizHandle quiz) throws Exception;

    public int getAnsweredQuestion(QuizQuizHandle quiz) throws Exception;
            
    public QuizQuizHandle getReviewQuiz(int quizTakeId) throws Exception;
}
