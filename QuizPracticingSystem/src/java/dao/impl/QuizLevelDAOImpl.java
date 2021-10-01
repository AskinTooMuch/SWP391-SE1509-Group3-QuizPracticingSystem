/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.util.ArrayList;
import bean.QuizLevel;
import dao.MyDAO;
import dao.QuizLevelINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizLevelDAOImpl extends MyDAO implements QuizLevelINT {

    @Override
    public ArrayList<QuizLevel> getAllQuizLevel() {
        ArrayList<QuizLevel> quizLevels = null;
        return quizLevels;
    }

    @Override
    public QuizLevel getQuizLevelById(int quizLevelId) {
        String sql = "SELECT * FROM [QuizLevel] where quizLevelId=" + quizLevelId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new QuizLevel(rs.getInt("quizLevelId"),
                        rs.getString("quizLevelName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel) {
        int i = 0;

        return i;
    }

    @Override
    public int addQuizLevel(QuizLevel quizLevel) {
        int i = 0;

        return i;
    }

    @Override
    public int deleteQuizLevel(int quizLevelId) {
        int i = 0;

        return i;
    }
}
