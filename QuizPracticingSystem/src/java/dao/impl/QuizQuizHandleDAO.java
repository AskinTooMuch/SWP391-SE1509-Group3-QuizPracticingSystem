/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Question;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;
import dao.QuizQuizHandleINT;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public class QuizQuizHandleDAO implements QuizQuizHandleINT {

    @Override
    public QuizQuizHandle generateQuiz(ArrayList<Question> questionList) {
        QuizQuizHandle quizz = new QuizQuizHandle();
        QuestionQuizHandleDAO questionQuizzHandleDAO = new QuestionQuizHandleDAO();

        for (Question question : questionList) {
            QuestionQuizHandle questionQh = questionQuizzHandleDAO.generateQuestionById(question.getQuestionId());
            quizz.addQuestion(questionQh);
        }
        return quizz;
    }
}
