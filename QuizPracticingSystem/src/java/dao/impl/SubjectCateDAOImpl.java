/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SubjectCateDAO
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         ChucNVHE150618  First Deploy
 *  24/9/21     1.1         ChucNVHE150618  Add methods: getSubjectCateBySubject
 *  6/10/21     1.2         ChucNVHE150618  Add methods: getAllSubjectCates
 */
package dao.impl;

import bean.SubjectCate;
import dao.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.SubjectCateDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SubjectCateDAOImpl extends DBConnection implements SubjectCateDAO {

    @Override
    public ArrayList<SubjectCate> getAllSubjectCates() throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */
        /* Get category list */
        ArrayList<SubjectCate> allCategory = new ArrayList<>();
        String sql = "SELECT [subjectCateId]\n" +
                    "      ,[subjectCateName]\n" +
                    "      ,[status]\n" +
                    "  FROM [QuizSystem].[dbo].[SubjectCate]\n";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allCategory.add(new SubjectCate(rs.getInt("subjectCateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return allCategory;
    }

    @Override
    public SubjectCate getSubjectCateById(int scId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SubjectCate> getSubjectCateBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */
        /* Getcategory list of the subject */
        ArrayList<SubjectCate> categories = new ArrayList<>();
        String sql = "SELECT C.[subjectId]\n"
                + "      ,C.[cateId]\n"
                + "	   ,S.[status]\n"
                + "	   ,S.subjectCateName\n"
                + "  FROM [QuizSystem].[dbo].[CategorySubject] C \n"
                + "  INNER JOIN [QuizSystem].[dbo].SubjectCate S\n"
                + "  ON C.cateId = S.subjectCateId WHERE C.subjectId =" + subjectId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                categories.add(new SubjectCate(rs.getInt("cateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return categories;
    }
    
    @Override
    public ArrayList<SubjectCate> getRemainSubjectCateBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */
        /* Getcategory list of the subject */
        ArrayList<SubjectCate> remainCategories = new ArrayList<>();
        String sql = "SELECT [subjectCateId]\n" +
                    "      ,[subjectCateName]\n" +
                    "      ,[status]\n" +
                    "  FROM [QuizSystem].[dbo].[SubjectCate]\n" +
                    "  WHERE [subjectCateId] NOT IN (SELECT C.[cateId]\n" +
                    "				FROM [QuizSystem].[dbo].[CategorySubject] C\n" +
                    "				INNER JOIN [QuizSystem].[dbo].SubjectCate S\n" +
                    "				ON C.cateId = S.subjectCateId WHERE C.subjectId = "+ subjectId +")";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                remainCategories.add(new SubjectCate(rs.getInt("subjectCateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return remainCategories;
    }

    @Override
    public int updateSubjectCate(SubjectCate updatedSubjectCate) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deteleSubjectCate(int scId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* Test DAO */
    public static void main(String[] args) {
        SubjectCateDAOImpl dao = new SubjectCateDAOImpl();
        try {
            System.out.println(dao.getAllSubjectCates().size());
            System.out.println(dao.getSubjectCateBySubject(1).size());
            System.out.println(dao.getRemainSubjectCateBySubject(1).size());
        } catch (Exception ex) {
            Logger.getLogger(SubjectCateDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
