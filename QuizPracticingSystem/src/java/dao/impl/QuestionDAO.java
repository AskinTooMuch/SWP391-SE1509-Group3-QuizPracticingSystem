/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import bean.Question;
import java.util.ArrayList;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class QuestionDAO extends MyDAO {

    public ArrayList<Question> getAllQuestion() {
        return null;
    }

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
    

    public int addQuestion(Question newQuestion) {
        return 0;
    }
    public int editQuestion(int questionId, Question editedQuestion){
        return 0;
    }
    public int deleteQuestion(int questionId){
        return 0;
    }
    public int importQuestion(ArrayList<Question> questionList) {
        return 0;
    }
    
}
