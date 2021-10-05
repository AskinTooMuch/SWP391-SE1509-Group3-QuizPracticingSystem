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
import dao.CustomerQuizDAO;
import dao.DBConnection;
import dao.QuestionDAO;
import java.util.ArrayList;
import dao.QuizQuizHandleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ADMN
 */
public class QuizQuizHandleDAOImpl extends DBConnection implements QuizQuizHandleDAO {

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
    public QuizQuizHandle generateQuiz(ArrayList<Question> questionList, int quizId) throws Exception {
        QuizQuizHandle quiz = new QuizQuizHandle();
        QuestionQuizHandleDAOImpl questionQuizzHandleDAO = new QuestionQuizHandleDAOImpl();
        QuizDAOImpl quizDAO = new QuizDAOImpl();
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
    public double calculateScore(QuizQuizHandle quiz) throws Exception {
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
    public int getAnsweredQuestion(QuizQuizHandle quiz) throws Exception {
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
    public QuizQuizHandle getReviewQuiz(int quizTakeId) throws Exception {
        QuestionDAO questionDAO = new QuestionDAOImpl();
        QuizQuizHandle reviewQuiz = new QuizQuizHandle();
        QuizDAOImpl quizDAO = new QuizDAOImpl();
        Quiz quiz = quizDAO.getQuizByQuizTakeId(quizTakeId);
        CustomerQuizDAO customerQuizInterface = new CustomerQuizDAOImpl();
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        AnswerDAOImpl answerDAO = new AnswerDAOImpl();
        ArrayList<QuestionQuizHandle> questionList = new ArrayList();
        String sql = "SELECT * FROM [TakeAnswer] WHERE quizTakeId=" + quizTakeId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Question question = questionDAO.getQuestionById(rs.getInt("questionId"));
                ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(rs.getInt("questionId"));
                questionList.add(new QuestionQuizHandle(question,
                        answers,
                        rs.getInt("answerId"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        reviewQuiz.setQuestions(questionList);
        reviewQuiz.setQuiz(quiz);
        reviewQuiz.setTime(customerQuizInterface.getQuizByTakeQuizId(quizTakeId).getTime());
        return reviewQuiz;
    }

//    public static void main(String[] args) {
//        QuizQuizHandleDAOImpl dao = new QuizQuizHandleDAOImpl();
//        QuestionDAOImpl qdao = new QuestionDAOImpl();
//        ArrayList<Question> s = qdao.getAllQuestion();
//
//        QuizQuizHandle list = dao.getReviewQuiz(11);
//        for (QuestionQuizHandle q : list.getQuestions()) {
//            System.out.println(q.getAnsweredId());
//        }
//    }
}
