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
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.AnswerDAO;
import dao.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class AnswerDAOImpl extends DBConnection implements AnswerDAO {

    @Override
    public ArrayList<Answer> getAllAnswers() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Answer> getAnswersByQuenstionId(int questionId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<Answer> answerList = new ArrayList();
        String sql = "SELECT * FROM Answer WHERE questionId=" + questionId;
        /* Sql query */
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                answerList.add(new Answer(rs.getInt("answerId"),
                        rs.getInt("questionId"),
                        rs.getString("answerContent"),
                        rs.getBoolean("isCorrect"),
                        rs.getBoolean("status")));
            }
            return answerList;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    @Override
    public int deleteAnswerById(int aId) throws Exception {
        return 0;
    }

    @Override
    public int deleteAnswerByQuestionId(int qId) throws Exception {
        return 0;
    }

    @Override
    public int updateAnswer(int answerId, Answer updatedAnswer) throws Exception {
        Connection conn = null;
        ResultSet rs = null;/* Result set returned by the sqlserver */
        PreparedStatement pre = null;/* Prepared statement for executing sql queries */
        int check = 0;
        String sql = "UPDATE [Answer]\n"
                + "SET [questionId] = ?\n"
                + "      ,[answerContent] =? \n"
                + "      ,[isCorrect] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [answerId] = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, updatedAnswer.getQuestionId());
            pre.setString(2, updatedAnswer.getAnswerContent());
            pre.setBoolean(3, updatedAnswer.isIsCorrect());
            pre.setBoolean(4, updatedAnswer.isStatus());
            pre.setInt(5, updatedAnswer.getAnswerId());
            check = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return check;
    }

    @Override
    public int addAnswer(Answer newAnswer) throws Exception {
        Connection conn = null;
        ResultSet rs = null;/* Result set returned by the sqlserver */
        PreparedStatement pre = null;/* Prepared statement for executing sql queries */

        String sql = "INSERT INTO [Answer](questionId,answerContent,isCorrect,status) "
                + "values (?,?,?,?)";
        int count = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, newAnswer.getQuestionId());
            pre.setString(2, newAnswer.getAnswerContent());
            pre.setBoolean(3, newAnswer.isIsCorrect());
            pre.setBoolean(4, newAnswer.isStatus());
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

}
