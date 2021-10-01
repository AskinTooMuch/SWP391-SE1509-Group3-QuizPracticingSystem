/* 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 17, 2021, 9:33:11 PM
    PostCateDAO
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    17/9/21     1.0         NamDHHE150519   First Deploy
    30/9/21     1.1         NamDHHE150519   update method
*/
/*
  Lớp này có các phương thức thực hiện truy xuất và ghi dữ liệu vào database liên
  quan tới bảng Question phục vụ cho các chức năng liên quan tới Question của 
  dự án
  @author Đinh Hải Nam
 */
package dao.impl;

import bean.Question;
import java.util.ArrayList;
import dao.MyDAO;
import dao.QuestionINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class QuestionDAOImpl extends MyDAO implements QuestionINT {

    /**
     * get all question from database
     *
     * @return list of all question. It is a <code>java.util.ArrayList</code> object.
     */
    @Override
    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> questionList = new ArrayList();
        String sql = "SELECT * FROM Question";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                questionList.add(new Question(rs.getInt("questionId"),
                        rs.getInt("subjectId"),
                        rs.getInt("dimensionId"),
                        rs.getInt("lessonId"),
                        rs.getString("content"),
                        rs.getString("media"),
                        rs.getString("explanation"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return questionList;
    }

    /**
     * turn a list of question into list of question quiz handle
     *
     * @param questionId the target question's id. It is a <code>int</code>
     * primitive type
     * @return a question. It is a <code>Question</code> object.
     */
    @Override
    public Question getQuestionById(int questionId) {
        String sql = "SELECT * FROM Question WHERE questionId=" + questionId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new Question(rs.getInt("questionId"),
                        rs.getInt("subjectId"),
                        rs.getInt("dimensionId"),
                        rs.getInt("lessonId"),
                        rs.getString("content"),
                        rs.getString("media"),
                        rs.getString("explanation"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    /**
     * get list of question of the target quiz 
     *
     * @param quizId the target quiz's id. It is a <code>int</code>
     * primitive type
     * @return a list of question. It is a <code>java.util.ArrayList</code> object.
     */
    @Override
    public ArrayList<Question> getQuestionByQuizId(int quizId) {
        ArrayList<Question> questionList = new ArrayList();
        ArrayList<Integer> idList = new ArrayList();
        String sql = "SELECT * FROM [QuizQuestion] WHERE quizId=" + quizId;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt("questionId"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        for (int id : idList) {
            questionList.add(getQuestionById(id));
        }
        return questionList;
    }

    @Override
    public int addQuestion(Question newQuestion) {
        return 0;
    }

    @Override
    public int editQuestion(int questionId, Question editedQuestion) {
        return 0;
    }

    @Override
    public int deleteQuestion(int questionId) {
        return 0;
    }

    @Override
    public int importQuestion(ArrayList<Question> questionList) {
        return 0;
    }

    public static void main(String[] args) {
        QuestionDAOImpl dao = new QuestionDAOImpl();

        ArrayList<Question> list = dao.getQuestionByQuizId(1);
        for (Question q : list) {
            System.out.println(q);
        }
    }

}
