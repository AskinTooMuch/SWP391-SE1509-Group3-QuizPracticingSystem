/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Answer;
import bean.Question;
import bean.QuestionQuizHandle;
import dao.QuestionQuizHandleINT;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public class QuestionQuizHandleDAO implements QuestionQuizHandleINT {

    @Override
    public QuestionQuizHandle generateQuestionById(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        Question question = questionDAO.getQuestionById(questionId);
        ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(questionId);
        return new QuestionQuizHandle(question, answers, 0,false);
    }
}
