/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Answer;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;

/**
 *
 * @author ADMN
 */
public interface QuestionQuizHandleINT {

    public QuestionQuizHandle generateQuestionById(int questionId);

    public void markQuestion(QuestionQuizHandle question);

    public Answer getRightAnswer(QuestionQuizHandle question);
}
