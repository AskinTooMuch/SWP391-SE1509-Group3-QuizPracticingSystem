/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         TuanPAHE150543   First Deploy
 *  20/10/21    1.1         TuanPAHE150543  Add method
 */
package dao.impl;

import bean.LessonType;
import dao.DBConnection;
import java.util.ArrayList;
import dao.LessonTypeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *  Lớp này chứa các method của LessonTypeDAOImpl
 * @author tuan
 */
public class LessonTypeDAOImpl extends DBConnection implements LessonTypeDAO {

    @Override
    public ArrayList<LessonType> getAllLessonType() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        ArrayList<LessonType> lessonTypesList = new ArrayList<>();
        String sql = "SELECT [lessonTypeId]\n"
                + "      ,[lessonTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[LessonType]\n"
                + "  WHERE status = 1 ";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                lessonTypesList.add(
                        new LessonType(rs.getInt("lessonTypeId"),
                                rs.getString("lessonTypeName"),
                                rs.getBoolean("status")));           
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return lessonTypesList;
    }

    @Override
    public LessonType getLessonTypeById(int ltId) throws Exception {
        return null;
    }

    @Override
    public int addLessonType(LessonType newLessonType) throws Exception {
        return 0;
    }

    @Override
    public int updateLessonType(LessonType updatedLessonType) throws Exception {
        return 0;
    }

    @Override
    public int deleteLessonType(int ltId) throws Exception {
        return 0;
    }
    
}

