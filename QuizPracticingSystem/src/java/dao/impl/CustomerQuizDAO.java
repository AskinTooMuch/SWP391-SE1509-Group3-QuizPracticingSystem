/* 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 17, 2021, 9:33:11 PM
    CustomerQuizDAO
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    17/9/21     1.0         ChucNVHE150618  First Deploy
    27/9/21     1.1         NamDHHE150519   update method
*/
/*
  Lớp này có các phương thức thực hiện truy xuất và ghi dữ liệu vào database liên
quan tới bảng CustomerQuiz, TakeAnswer phục vụ cho các chức năng liên quan tới QuizReview của 
  dự án
  @author Đinh Hải Nam
 */
package dao.impl;

import bean.CustomerQuiz;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;
import dao.CustomerQuizINT;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ChucNVHE150618
 */
public class CustomerQuizDAO extends MyDAO implements CustomerQuizINT {

    @Override
    public ArrayList<CustomerQuiz> getAllCustomerQuiz() {
        ArrayList<CustomerQuiz> allCustomerQuiz = null;

        return allCustomerQuiz;
    }

    @Override
    public ArrayList<CustomerQuiz> getQuizByUser(int userId) {
        ArrayList<CustomerQuiz> customerQuiz = null;

        return customerQuiz;
    }

    @Override
    public CustomerQuiz getQuizById(int quizId) {
        CustomerQuiz customerQuiz = null;

        return customerQuiz;
    }

    @Override
    public int editCustomerQuiz(int customerQuizId, CustomerQuiz customerQuiz) {
        int i = 0;

        return i;
    }

    /**
     * insert into CustomerQuiz table the quiz that just have taken by user
     *
     * @param customerQuiz the new CustomerQuiz. It is a <code>CustomerQuiz</code> object
     * @return number of changes in database. It is a <code>int</code> primitive type.
     */
    @Override
    public int addCustomerQuiz(CustomerQuiz customerQuiz) {
        String sql = "INSERT INTO [CustomerQuiz](quizId,userId,score,[time],startedAt,[status]) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, customerQuiz.getQuizId());
            pre.setInt(2, customerQuiz.getUserId());
            pre.setInt(3, customerQuiz.getScore());
            pre.setInt(4, customerQuiz.getTime());
            pre.setDate(5, customerQuiz.getStartedAt());
            pre.setBoolean(6, true);
            return pre.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);
        }
        return 0;
    }

    /**
     * get the last added customer quiz
     *
     * @return a customer quiz. It is a <code>CustomerQuiz</code> object.
     */
    public CustomerQuiz getLastAddedCustomerQuiz() {
        String sql = "SELECT TOP 1 * FROM [CustomerQuiz] ORDER BY quizTakeId DESC";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new CustomerQuiz(rs.getInt("quizTakeId"),
                        rs.getInt("quizId"),
                        rs.getInt("userId"),
                        rs.getInt("score"),
                        rs.getInt("time"),
                        rs.getDate("startedAt"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return null;
    }

    @Override
    public int deleteCustomerQuiz(int customerQuizId) {
        int i = 0;

        return i;
    }
/**
     * add user's answer of the taken quiz into database
     *
     * @param quiz the id of quiz that user have just taken. It is a <code>QuizQuizHandle</code> object
     * @return number of changes in database <code>int</code> primitive type.
     */
    @Override
    public int addTakeAnswer(QuizQuizHandle quiz) {
        int change = 0;
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        int quizTakeId = getLastAddedCustomerQuiz().getQuizTakeId();
        for (QuestionQuizHandle question : questionList) {
            if (question.getAnsweredId() != 0) {
                String sql = "INSERT INTO [TakeAnswer](quizTakeId,questionId,answerId,[status]) VALUES(?,?,?,?)";
                try {
                    PreparedStatement pre = conn.prepareStatement(sql);
                    pre.setInt(1, quizTakeId);
                    pre.setInt(2, question.getQuestion().getQuestionId());
                    pre.setInt(3, question.getAnsweredId());
                    pre.setBoolean(4, true);
                    pre.executeUpdate();
                    change++;
                } catch (SQLException e) {
                    System.out.print(e);
                }
            }
        }
        return change;
    }
/**
     * insert into the database a list of questions marked by the quiz the user has just taken 
     *
    * @param quiz the quiz the user has just taken. It is a <code>QuizQuizHandle</code> object
     * @return number of changes in database. It is a <code>int</code> primitive type
     */
    @Override
    public int addMarkQuestion(QuizQuizHandle quiz) {
        int change = 0;
        ArrayList<QuestionQuizHandle> questionList = quiz.getQuestions();
        int quizTakeId = getLastAddedCustomerQuiz().getQuizTakeId();
        for (QuestionQuizHandle question : questionList) {
            String sql = "INSERT INTO [MarkQuestion](quizTakeId,questionId,[status]) VALUES(?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setInt(1, quizTakeId);
                pre.setInt(2, question.getQuestion().getQuestionId());
                pre.setBoolean(3, question.isMarked());
                pre.executeUpdate();
                change++;
            } catch (SQLException e) {
                System.out.print(e);
            }
        }
        return change;
    }

    public static void main(String[] args) {
        CustomerQuizDAO dao = new CustomerQuizDAO();
        String[] a = {"1", "2"};
        CustomerQuiz list = dao.getLastAddedCustomerQuiz();
        System.out.print(list.getQuizTakeId());
    }
}
