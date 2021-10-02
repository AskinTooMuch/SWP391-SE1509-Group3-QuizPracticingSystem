/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    @Override
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception {
        ArrayList<QuizLevel> quizLevels = null;
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
