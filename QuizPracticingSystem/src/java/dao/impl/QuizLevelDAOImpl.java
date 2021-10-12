/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         DuongNHHE150328  First Deploy
 *  10/10/21    1.1         DuongNHHE150328  Add new method
 *  11/10/21    1.1         DuongNHHE150328  Add new method
 */
package dao.impl;

import java.util.ArrayList;
import bean.QuizLevel;
import dao.DBConnection;
import java.sql.PreparedStatement;
import dao.QuizLevelDAO;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizLevelDAOImpl extends DBConnection implements QuizLevelDAO {

    /**
     * get all quizlevel in the database
     * @return <code>ArrayList<QuizLevel></code>
     * @throws Exception 
     */
    @Override
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception {
        ArrayList<QuizLevel> quizLevels = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        QuizLevel quizLevel = null;
        String sql = "SELECT [quizLevelId]\n"
                + "      ,[quizLevelName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[QuizLevel]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                quizLevel =  new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
                        rs.getBoolean("status"));
                quizLevels.add(quizLevel);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return quizLevels;
    }

    @Override
    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [QuizLevel] where quizLevelId=" + quizLevelId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
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

    @Override
    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel) throws Exception {
        int i = 0;

        return i;
    }

    @Override
    public int addQuizLevel(QuizLevel quizLevel) throws Exception {
        int i = 0;

        return i;
    }

    @Override
    public int deleteQuizLevel(int quizLevelId) throws Exception {
        int i = 0;

        return i;
    }
}
