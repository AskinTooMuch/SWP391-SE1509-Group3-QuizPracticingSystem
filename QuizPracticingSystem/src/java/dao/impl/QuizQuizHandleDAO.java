/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Answer;
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
            int questionId = question.getQuestionId();
            QuestionQuizHandle questionQH = questionQuizzHandleDAO.generateQuestionById(questionId);
            quiz.addQuestion(questionQH);
        }
        return quiz;
    }
    @Override
    public double calculateScore(QuizQuizHandle quiz) {
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        ArrayList<Integer> rightAnswerList = new ArrayList();
        double rightAnsweredCount = 0;
        for (QuestionQuizHandle question : questionList) {
            for (Answer answer : question.getAnswerList()) {
                if (answer.isIsCorrect()) {
                    rightAnswerList.add(answer.getAnswerId());
                }
            }
        }
        int questionNo = 0;
        for (QuestionQuizHandle question : questionList) {

            if (question.getAnsweredId() == rightAnswerList.get(questionNo)) {
                rightAnsweredCount++;
            }
            questionNo += 1;
        }
        double score = (rightAnsweredCount/questionList.size())*100;
        return score;
    }
    
    @Override
    public int getAnsweredQuestion(QuizQuizHandle quiz){
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        int count=0;
        for(QuestionQuizHandle question : questionList){
            if(question.getAnsweredId()!=0){
                count+=1;
            }
        }
        return count;
    }
    
    
   

    public static void main(String[] args) {
        QuizQuizHandleDAO dao = new QuizQuizHandleDAO();
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Question> q = qdao.getAllQuestion();
        
        QuizQuizHandle list = dao.generateQuiz(q);
        list.getQuestions().get(0).setAnsweredId(1);
       
        System.out.print(dao.calculateScore(list));
        
    }
}
