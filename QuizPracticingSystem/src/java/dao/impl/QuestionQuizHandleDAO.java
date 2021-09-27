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
    //Turn a Question into QuestionQuizHandle
    public QuestionQuizHandle generateQuestionById(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        Question question = questionDAO.getQuestionById(questionId);                        //get question
        ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(questionId);          //get question's answer list
        return new QuestionQuizHandle(question, answers, 0, false);                         //constructor(question,question's answers list, user's answerd id,marked)
    }

    @Override
    //mark and unmark question
    public void markQuestion(QuestionQuizHandle question) {
        if (question.isMarked()) {
            question.setMarked(false);
        } else {
            question.setMarked(true);
        }
    }
    
    @Override
    //get the right answer of question (peek at answer)
    public Answer getRightAnswer(QuestionQuizHandle question) {
        ArrayList<Answer> answerList = question.getAnswerList();
        for (Answer answer : answerList) {
            if (answer.isIsCorrect()) {
                return answer;
            }
        }
        return null;
    }
}
