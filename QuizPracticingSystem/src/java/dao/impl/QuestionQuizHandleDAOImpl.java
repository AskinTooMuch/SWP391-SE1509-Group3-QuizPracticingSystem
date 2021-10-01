/* 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 17, 2021, 9:33:11 PM
    QuestionQuizHandleDAO
    Record of change:
    Date        Version     Author          Description
    17/9/21     1.0         NamDHHE150519   First Deploy
    30/9/21     1.1         NamDHHE150519   update method
*/
/*
  Lớp này có các phương thức thực hiện tạo ra những câu hỏi trong bài quiz bằng 
  các câu hỏi lấy từ database, kết hợp với QuizQuizHandle để
  phục vụ funtion QuizHandle hoặc QuizReview
  @author Đinh Hải Nam
 */
package dao.impl;

import bean.Answer;
import bean.Question;
import bean.QuestionQuizHandle;
import dao.MyDAO;
import dao.QuestionQuizHandleINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionQuizHandleDAOImpl extends MyDAO implements QuestionQuizHandleINT {

    @Override
    /**
     * turn a Question into QuestionQUizHandle type
     *
     * @param questionId the target question's id. It is a <code>int</code>
     * primitive type
     * @return a QuestionQuizHandle <code>QuestionQuizHandle</code> object.
     */
    public QuestionQuizHandle generateQuestionById(int questionId) {
        QuestionDAOImpl questionDAO = new QuestionDAOImpl();
        AnswerDAO answerDAO = new AnswerDAO();
        Question question = questionDAO.getQuestionById(questionId);                        //get question
        ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(questionId);          //get question's answer list
        return new QuestionQuizHandle(question, answers, 0, false);                         //constructor(question,question's answers list, user's answerd id,marked)
    }

    @Override
/**
     * mark and unmark question
     *
     * @param question the target question's id. It is a <code>QuestionQuizHandle</code> object
     * @return void.
     */
    public void markQuestion(QuestionQuizHandle question) {
        if (question.isMarked()) {
            question.setMarked(false);
        } else {
            question.setMarked(true);
        }
    }

    @Override
    /**
     * get right answer of the question
     *
     * @param question the target question's id. It is a <code>QuestionQuizHandle</code>
     * object
     * @return right answer of the question. It is <code>Answer</code> object.
     */
    public Answer getRightAnswer(QuestionQuizHandle question) {
        ArrayList<Answer> answerList = question.getAnswerList();
        for (Answer answer : answerList) {
            if (answer.isIsCorrect()) {
                return answer;
            }
        }
        return null;
    }
    /**
     * get list of mark question from taken quiz
     *
     * @param quizTakeId the target taken quiz's id. It is a <code>int</code>
     * primitive type
     * @return a list of boolean. It is a <code>java.util.ArrayList</code> object.
     */
    @Override
    public ArrayList<Boolean> getMarkQuestionList(int quizTakeId) {
        ArrayList<Boolean> markQuestionList = new ArrayList();
        String sql = "SELECT * FROM [MarkQuestion] WHERE quizTakeId =" + quizTakeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                markQuestionList.add(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
        }
        return markQuestionList;
    }

    /**
     * get the taken quiz for review action
     *
     * @param quizTakeId the target taken quiz's id. It is a <code>int</code>
     * primitive type
     * @return a <code>QuizQuizHandle</code> object.
     */
    @Override
    public ArrayList<QuestionQuizHandle> getReviewQuestion(int quizTakeId) {
        QuestionQuizHandleDAOImpl questionQuizHandleDAO = new QuestionQuizHandleDAOImpl();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl();
        AnswerDAO answerDAO = new AnswerDAO();
        ArrayList<QuestionQuizHandle> questionList = new ArrayList();
       
        String sql = "SELECT * FROM [TakeAnswer] WHERE quizTakeId=" + quizTakeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Question question = questionDAO.getQuestionById(rs.getInt("questionId"));
                ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(rs.getInt("questionId"));
                questionList.add(new QuestionQuizHandle(question,
                        answers,
                        rs.getInt("answerId"),
                        false));
              
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        
        return questionList;
    }

    public static void main(String[] args) {
        QuestionQuizHandleDAOImpl dao = new QuestionQuizHandleDAOImpl();
        QuestionDAOImpl qdao = new QuestionDAOImpl();
        ArrayList<Boolean> s = dao.getMarkQuestionList(11);
        for (boolean a : s) {
            System.out.println(a);
        }
    }
}
