/* 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 17, 2021, 9:33:11 PM
    Record of change:
    Date        Version     Author          Description
    17/9/21     1.0         NamDHHE150519   First Deploy
    30/9/21     1.1         NamDHHE150519   update method
 */
 /*
  Lớp này có các phương thức thực hiện tạo ra những bài quiz từ các câu hỏi 
  lấy từ database phục vụ funtion QuizHandle hoặc QuizReview
  @author Đinh Hải Nam
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
public class QuizQuizHandleDAOImpl implements QuizQuizHandleINT {

    /**
     * turn a list of question into list of question quiz handle
     *
     * @param questionList target question list. It is a
     * <code>java.util.ArrayList</code> object
     * @param quizId the Id of the quiz whose above questionList. It is
     * <code>int</code> primitive type
     *
     * @return a <code>QuizQuizHandle</code> object.
     */
    @Override
    public QuizQuizHandle generateQuiz(ArrayList<Question> questionList, int quizId) {
        QuizQuizHandle quiz = new QuizQuizHandle();
        QuestionQuizHandleDAOImpl questionQuizzHandleDAO = new QuestionQuizHandleDAOImpl();
        QuizDAO quizDAO = new QuizDAO();
        Quiz quizInDatabase = quizDAO.getQuizById(quizId);
        for (Question question : questionList) {
            int questionId = question.getQuestionId();
            QuestionQuizHandle questionQH = questionQuizzHandleDAO.generateQuestionById(questionId);//turn a question into question quiz handle
            quiz.addQuestion(questionQH);                                                           //add question to list           
        }
        quiz.setQuiz(quizInDatabase);
        quiz.setTime(quizInDatabase.getQuizDuration());
        return quiz;
    }

    /**
     * calculate score of the quiz
     *
     * @param quiz the target calculateScore quiz. It is a
     * <code>QuizQuizHandle</code> object
     * @return a <code>QuizQuizHandle</code> object.
     */
    @Override
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

    /**
     * get number of answered question (submited quiz)
     *
     * @param quiz the target quiz. It is a <code>QuizQuizHandle</code> object
     * @return a <code>QuizQuizHandle</code> object.
     */
    @Override
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

    /**
     * turn a list of question into list of question quiz handle
     *
     * @param quizTakeId the target quiz's id. It is a <code>int</code>
     * primitive type
     * @return a <code>QuizQuizHandle</code> object.
     */
    @Override
    public QuizQuizHandle getReviewQuiz(int quizTakeId) {
        QuestionQuizHandleDAOImpl questionQuizHandleDAO = new QuestionQuizHandleDAOImpl();
        QuizQuizHandle reviewQuiz = new QuizQuizHandle();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl();
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
                    skip = true;
                }
            }
            //If question had not answered then not in database
            if (!skip) {
                QuestionQuizHandle emptyReviewQuestion = questionQuizHandleDAO.generateQuestionById(questionId);
                emptyReviewQuestion.setAnsweredId(0);
                reviewQuiz.addQuestion(emptyReviewQuestion);
            }
        }

        for (QuestionQuizHandle question : reviewQuiz.getQuestions()) {
            question.setMarked(markQuestionList.get(reviewQuiz
                    .getQuestions()
                    .indexOf(question)));
        }
        return reviewQuiz;
    }

    public static void main(String[] args) {
        QuizQuizHandleDAOImpl dao = new QuizQuizHandleDAOImpl();
        QuestionDAOImpl qdao = new QuestionDAOImpl();
        ArrayList<Question> s = qdao.getAllQuestion();

        QuizQuizHandle list = dao.getReviewQuiz(11);
        for (QuestionQuizHandle q : list.getQuestions()) {
            System.out.println(q.getAnsweredId());
        }
    }
}
