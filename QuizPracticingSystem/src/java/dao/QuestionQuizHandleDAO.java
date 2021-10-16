/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Answer;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public interface QuestionQuizHandleDAO {

    public QuestionQuizHandle generateQuestionById(int questionId) throws Exception;

    public void markQuestion(QuestionQuizHandle question) throws Exception;

    public Answer getRightAnswer(QuestionQuizHandle question) throws Exception;

    public ArrayList<QuestionQuizHandle> getReviewQuestion(int quizTakeId) throws Exception;
}
