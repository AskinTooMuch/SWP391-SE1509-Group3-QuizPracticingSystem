/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import bean.Question;
import java.util.ArrayList;
import dao.MyDAO;
import dao.QuestionINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class QuestionDAO extends MyDAO implements QuestionINT {
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
@Override
    public Question getQuestionById(int questionId) {
        String sql = "SELECT * FROM Question WHERE questionId="+questionId;
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
    
@Override
    public int addQuestion(Question newQuestion) {
        return 0;
    }
    @Override
    public int editQuestion(int questionId, Question editedQuestion){
        return 0;
    }
    @Override
    public int deleteQuestion(int questionId){
        return 0;
    }
    @Override
    public int importQuestion(ArrayList<Question> questionList) {
        return 0;
    }   
    public static void main(String[] args) {
        QuestionDAO dao = new QuestionDAO();
       
        ArrayList<Question> list = dao.getAllQuestion();
        for (Question o : list) {
            System.out.println(o);
        }
    }
    
}
