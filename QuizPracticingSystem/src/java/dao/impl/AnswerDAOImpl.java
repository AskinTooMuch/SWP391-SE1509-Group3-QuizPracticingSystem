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
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.AnswerDAO;

/**
 *
 * @author duong
 */
public class AnswerDAOImpl extends MyDAO implements AnswerDAO {

    @Override
    public ArrayList<Answer> getAllAnswers() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Answer> getAnswersByQuenstionId(int questionId) throws Exception {
        ArrayList<Answer> answerList = new ArrayList();
        String sql = "SELECT * FROM Answer WHERE questionId=" + questionId;
        PreparedStatement pre = conn.prepareStatement(sql);
        rs = pre.executeQuery();
        while (rs.next()) {
            answerList.add(new Answer(rs.getInt("answerId"),
                    rs.getInt("questionId"),
                    rs.getString("answerContent"),
                    rs.getBoolean("isCorrect"),
                    rs.getBoolean("status")));
        }
        return answerList;
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
    public int updateAnswer(Answer updatedAns) throws Exception {
        return 0;
    }

    @Override
    public int addAnswer(Answer newAns) throws Exception {
        return 0;
    }

}
