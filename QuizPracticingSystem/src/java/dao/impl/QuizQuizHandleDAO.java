/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Answer;
import bean.Question;
import bean.QuestionQuizHandle;
import bean.Quiz;
import bean.QuizQuizHandle;
import dao.QuizQuizHandleINT;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public class QuizQuizHandleDAO implements QuizQuizHandleINT {
    
    @Override
    //turn a list of question into list of question quiz handle
    public QuizQuizHandle generateQuiz(ArrayList<Question> questionList) {
        QuizQuizHandle quiz = new QuizQuizHandle();
        QuestionQuizHandleDAO questionQuizzHandleDAO = new QuestionQuizHandleDAO();
        
        for (Question question : questionList) {
            int questionId = question.getQuestionId();
            QuestionQuizHandle questionQH = questionQuizzHandleDAO.generateQuestionById(questionId);//turn a question into question quiz handle
            quiz.addQuestion(questionQH);                                                           //add question to list 
        }
        return quiz;
    }
    
    @Override
    //calculaet score of the quiz
    public double calculateScore(QuizQuizHandle quiz) {
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        ArrayList<Integer> rightAnswerList = new ArrayList();                       //An array of right answerid only 
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
            
            if (question.getAnsweredId() == rightAnswerList.get(questionNo)) {      //for each question, compare question's answeredId with the same index
                rightAnsweredCount++;                                               //of the array of the right answerId
            }
            questionNo += 1;
        }
        double score = (rightAnsweredCount / questionList.size()) * 100;
        return score;
    }
    
    @Override
    //get number of answered question (submit quiz)
    public int getAnsweredQuestion(QuizQuizHandle quiz) {
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        int count = 0;
        for (QuestionQuizHandle question : questionList) {
            if (question.getAnsweredId() != 0) {
                count += 1;
            }
        }
        return count;
    }
    
    @Override
    public QuizQuizHandle getReviewQuiz(int quizTakeId) {
        QuestionQuizHandleDAO questionQuizHandleDAO = new QuestionQuizHandleDAO();
        QuizQuizHandle reviewQuiz = new QuizQuizHandle();
        QuestionDAO questionDAO = new QuestionDAO();
        QuizDAO quizDAO = new QuizDAO();
        Quiz quiz = quizDAO.getQuizByQuizTakeId(quizTakeId);
        ArrayList<QuestionQuizHandle> reviewQuestionList = questionQuizHandleDAO.getReviewQuestion(quizTakeId);     //Questions answered
        ArrayList<Question> questionList = questionDAO.getQuestionByQuizId(quiz.getQuizId());                             //All question of quiz
        ArrayList<Boolean> markQuestionList = questionQuizHandleDAO.getMarkQuestionList(quizTakeId);
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            int questionId = question.getQuestionId();
            boolean skip = false;
            for (QuestionQuizHandle reviewQuestion : reviewQuestionList) {
                int reviewQuestionId = reviewQuestion.getQuestion()
                        .getQuestionId();
                if (questionId == reviewQuestionId) {                                                                   //If questions answered
                    reviewQuiz.addQuestion(reviewQuestion); 
                    skip=true;
                }
            }
            //If question had not answered then not in database
            if(!skip){
            QuestionQuizHandle emptyReviewQuestion = questionQuizHandleDAO.generateQuestionById(questionId);
            emptyReviewQuestion.setAnsweredId(0);
            reviewQuiz.addQuestion(emptyReviewQuestion);} 
        }
        
         for(QuestionQuizHandle question: reviewQuiz.getQuestions()){
            question.setMarked(markQuestionList.get(reviewQuiz
                                            .getQuestions()
                                            .indexOf(question)));
        }
        return reviewQuiz;
    }
    
    public static void main(String[] args) {
        QuizQuizHandleDAO dao = new QuizQuizHandleDAO();
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Question> s = qdao.getAllQuestion();
        
        QuizQuizHandle list = dao.getReviewQuiz(11);
        for (QuestionQuizHandle q : list.getQuestions()) {
            System.out.println(q.getAnsweredId());
        }
    }
}
