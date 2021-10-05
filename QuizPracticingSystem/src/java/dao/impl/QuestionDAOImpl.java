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
import dao.DBConnection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import dao.QuestionDAO;
import java.sql.Connection;
import java.sql.ResultSet;

public class QuestionDAOImpl extends DBConnection implements QuestionDAO {

    /**
     * get all question from database
     *
     * @return list of all question. It is a <code>java.util.ArrayList</code>
     * object.
     */
    @Override
    public ArrayList<Question> getAllQuestion() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<Question> questionList = new ArrayList();
        String sql = "SELECT * FROM Question";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
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
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
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
    public Question getQuestionById(int questionId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "SELECT * FROM Question WHERE questionId=" + questionId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
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
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    /**
     * get list of question of the target quiz
     *
     * @param quizId the target quiz's id. It is a <code>int</code> primitive
     * type
     * @return a list of question. It is a <code>java.util.ArrayList</code>
     * object.
     */
    @Override
    public ArrayList<Question> getQuestionByQuizId(int quizId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<Question> questionList = new ArrayList();
        ArrayList<Integer> idList = new ArrayList();
        String sql = "SELECT * FROM [QuizQuestion] WHERE quizId=" + quizId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt("questionId"));
            }
            for (int id : idList) {
                questionList.add(getQuestionById(id));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return questionList;
    }

    @Override
    public ArrayList<Question> getQuestionByContent(String content) throws Exception{
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */       
        ArrayList<Question> listQuestion = new ArrayList<>();
        String sql = "SELECT * FROM [Question] WHERE status=1 and content like '%" + content + "%'";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setSubjectId(rs.getInt("subjectId"));
                question.setDimensionId(rs.getInt("dimensionId"));
                question.setLessonId(rs.getInt("lessonId"));
                question.setContent(rs.getString("content"));
                question.setMedia(rs.getString("meadia"));
                question.setExplanation(rs.getString("explanation"));
                question.setStatus(rs.getBoolean("status"));
                listQuestion.add(question);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return listQuestion;
    }

    @Override
    public int addQuestion(Question newQuestion) throws Exception {
        Connection conn = null;
        ResultSet rs = null;/* Result set returned by the sqlserver */
        PreparedStatement pre = null;/* Prepared statement for executing sql queries */
        
        String sql = "INSERT INTO [Question](subjectId,dimensionId,lessonId,content,media,explanation,status) values (?,?,?,?,?,?,?)";
        int count =0;
        try {
            pre = conn.prepareStatement(sql);
            pre.setInt(1, newQuestion.getSubjectId());
            pre.setInt(2, newQuestion.getDimensionId());
            pre.setInt(3, newQuestion.getLessonId());
            pre.setString(4, newQuestion.getContent());
            pre.setString(5, newQuestion.getMedia());
            pre.setString(6, newQuestion.getExplanation());
            pre.setBoolean(7, newQuestion.isStatus());
            count = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return count;
    }

    @Override
    public int editQuestion(int questionId, Question editedQuestion) throws Exception {
        return 0;
    }

    @Override
    public int deleteQuestion(int questionId) throws Exception {
        return 0;
    }

    @Override
    public int importQuestion(ArrayList<Question> questionList) throws Exception {
        return 0;
    }

}
