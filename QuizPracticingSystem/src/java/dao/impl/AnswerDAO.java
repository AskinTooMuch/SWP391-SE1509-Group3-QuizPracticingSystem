/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         DuongNHHE150328  First Deploy
*/
package dao.impl;
import bean.*;
import dao.AnswerINT;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author duong
 */
public class AnswerDAO extends MyDAO implements AnswerINT{
    
    @Override
    public ArrayList<Answer> getAllAnswers(){
        return null;
    }
    
    @Override
    public ArrayList<Answer> getAnswersByQuenstionId(int questionId){
        ArrayList<Answer> answerList = new ArrayList();
        String sql="SELECT * FROM Answer WHERE questionId="+questionId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                answerList.add(new Answer(rs.getInt("answerId"),
                rs.getInt("questionId"),
                rs.getString("answerContent"),
                rs.getBoolean("isCorrect"),
                rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return answerList;
    }
    
    @Override
    public int deleteAnswerById(int aId){
        return 0;
    }
    
    @Override
    public int deleteAnswerByQuestionId( int qId) {
        return 0;
    }
    
    @Override
    public int updateAnswer(Answer updatedAns){
        return 0;
    }
    
    @Override
    public int addAnswer(Answer newAns){
        return 0;
    }
    
}
