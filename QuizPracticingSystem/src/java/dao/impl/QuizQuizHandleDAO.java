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
        QuizQuizHandle quiz = new QuizQuizHandle();
        QuestionQuizHandleDAO questionQuizzHandleDAO = new QuestionQuizHandleDAO();

        for (Question question : questionList) {
            QuestionQuizHandle questionQH = questionQuizzHandleDAO.generateQuestionById(question.getQuestionId());
            quiz.addQuestion(questionQH);
        }
        return quiz;
    }
       public static void main(String[] args) {
        QuizQuizHandleDAO dao = new QuizQuizHandleDAO();
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Question> q = qdao.getAllQuestion();
    
        QuizQuizHandle list = dao.generateQuiz(q);
        
        for (QuestionQuizHandle o : list.getQuestions()) {
            System.out.println(o);
        }
        for (Question a : q){
            System.out.println(a);
        }
    }
}
