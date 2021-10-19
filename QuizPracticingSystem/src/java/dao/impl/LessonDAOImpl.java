/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  LessonDAOImpl
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/09/21     1.0        TuanPAHE150543      First Deploy

*/
package dao.impl;

import bean.Lesson;
import dao.DBConnection;
import java.util.ArrayList;
import dao.LessonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tuan
 */
public class LessonDAOImpl extends DBConnection implements LessonDAO {
    
    /**
     * Get all lessons from database
     *
     * @return a list of <code>Lesson</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Lesson> getAllLessons() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<Lesson> listLesson = new ArrayList();
        String sql = "SELECT [lessonId]\n"
                + "      ,[subjectId]\n"
                + "      ,[lessonName]\n"
                + "      ,[lessonOrder]\n"
                + "      ,[lessonTypeId]\n"
                + "      ,[videoLink]\n"
                + "      ,[content]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[Lesson]";
        /* Get the dimension */
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            /* Get information from resultset and add it to arrayList */
            while (rs.next()) {
                int lessonId = rs.getInt("lessonId");
                int subjectId = rs.getInt("subjectId");
                String lessonName = rs.getString("lessonName");
                int lessonOrder = rs.getInt("lessonOrder");
                int lessonTypeId = rs.getInt("lessonTypeId");
                String videoLink = rs.getString("videoLink");
                String content = rs.getString("content");
                Boolean status = rs.getBoolean("status");
                listLesson.add(new Lesson(lessonId, subjectId, lessonName, lessonOrder, lessonTypeId, videoLink, content, status, null));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return listLesson;
    }

    @Override
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId) throws Exception {
        return null;
    }
    
    /**
     * Get lesson from database by lesson's id
     *
     * @param lessonId lesson's ID. It is a <code>Integer</code>
     * @return a <code>Lesson</code> objects
     * @throws java.lang.Exception
     */
    @Override
    public Lesson getLessonById(int lessonId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        Lesson lessonById = null;
        String sql = "SELECT * FROM [Lesson] WHERE [lessonId] = ?";

        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, lessonId);
            rs = pre.executeQuery();
            if (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String lessonName = rs.getString("lessonName");
                int lessonOrder = rs.getInt("lessonOrder");
                int lessonTypeId = rs.getInt("lessonTypeId");
                String videoLink = rs.getString("videoLink");
                String content = rs.getString("content");
                Boolean status = rs.getBoolean("status");

                lessonById = new Lesson(lessonId, subjectId, lessonName, lessonOrder, lessonTypeId, videoLink, content, status, null);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return lessonById;
    }

    @Override
    public int updateLesson(Lesson updatedLesson) throws Exception {
        return 0;
    }

    @Override
    public int deleteLesson(int lessonId) throws Exception {
        return 0;
    }

    @Override
    public int addLesson(Lesson newLesson) throws Exception {
        return 0;
    }
}
