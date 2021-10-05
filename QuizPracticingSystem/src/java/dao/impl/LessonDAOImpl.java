
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
public class LessonDAOImpl extends DBConnection implements LessonDAO{
    @Override
    public ArrayList<Lesson> getAllLessons() throws Exception{
        return null;
    }
    
    @Override
    public ArrayList<Lesson> getAllLessonBySubjectId(int subId) throws Exception{
        return null;
    }
    
    @Override
    public ArrayList<Lesson> getAllLessonByTypeId(int typeId) throws Exception{
        return null;
    }
    
    @Override
    public Lesson getLessonById(int lessonId) throws Exception{
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        Lesson lessonById = null;
        String sql = "SELECT * FROM [Lesson] WHERE [lessonId] = " + lessonId;

        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String lessonName = rs.getString("lessonName");
                int lessonOrder = rs.getInt("lessonOrder");
                int lessonTypeId = rs.getInt("lessonTypeId");
                String videoLink = rs.getString("videoLink");
                String content = rs.getString("content");
                Boolean status = rs.getBoolean("status");
                lessonById = new Lesson(lessonId, subjectId, lessonName, lessonOrder, lessonTypeId, videoLink, content, true, null);
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
    public int updateLesson(Lesson updatedLesson) throws Exception{
        return 0;
    }
    
    @Override
    public int deleteLesson(int lessonId) throws Exception{
        return 0;
    }
    
    @Override
    public int addLesson(Lesson newLesson) throws Exception{
        return 0;
    }
}
