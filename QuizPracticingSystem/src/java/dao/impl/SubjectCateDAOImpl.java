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
 *  7/10/21     1.3         ChucNVHE150618  Add methods: getSubjectCateIdBySubject, addCategorySubject, deleteCategorySubject
 */
package dao.impl;

import bean.SubjectCate;
import dao.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.SubjectCateDAO;
import java.sql.Connection;
import java.sql.ResultSet;

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
                + "  ON C.cateId = S.subjectCateId WHERE C.subjectId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
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
    public String[] getSubjectCateIdBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */
        /* Getcategory list of the subject */
        ArrayList<String> categoryId = new ArrayList<>();
        String sql = "SELECT C.[cateId]\n"
                + "  FROM [QuizSystem].[dbo].[CategorySubject] C \n"
                + "  INNER JOIN [QuizSystem].[dbo].SubjectCate S\n"
                + "  ON C.cateId = S.subjectCateId WHERE C.subjectId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
            rs = pre.executeQuery();
            while (rs.next()) {
                categoryId.add(rs.getString("cateId"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        
        String[] idString = new String[categoryId.size()];
        return categoryId.toArray(idString);
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
                    "				ON C.cateId = S.subjectCateId WHERE C.subjectId = ?)";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
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
    public int addSubjectCate(SubjectCate updatedSubjectCate) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addCategorySubject(int subjectId, int categoryId) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

        String sql = "INSERT INTO dbo.CategorySubject(subjectId,cateId) "
                + "VALUES(?,?)";
        int check = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
            pre.setInt(2, categoryId);
            check = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return check;
    }
    
    @Override
    public int updateSubjectCate(int subjectCategoryId, SubjectCate updatedSubjectCate) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int updateSubjectContentCate(int subjectId, String[] updatedSubjectCateId) throws Exception {
        Connection conn = null;
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

        String[] currentCategorySubject = getSubjectCateIdBySubject(subjectId);
        int check = 0;
        /* Terminates: eliminates all the unchanged tuples */
        if (updatedSubjectCateId != null) {
            for (int i = 0; i < updatedSubjectCateId.length; i++) {
                for (int j = 0; j < currentCategorySubject.length; j++) {
                    if (updatedSubjectCateId[i].equals(currentCategorySubject[j])) {
                        updatedSubjectCateId[i]= "-1";
                        currentCategorySubject[j] = "-1";
                    }
                }
            }
            
            try {
                for (String categoryId : updatedSubjectCateId) {
                    if (!categoryId.equals("-1"))
                    check += addCategorySubject(subjectId, Integer.parseInt(categoryId));
                }
                for (String categoryId : currentCategorySubject) {
                    if (!categoryId.equals("-1"))
                    check += deteleCategorySubject(subjectId, Integer.parseInt(categoryId));
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                closePreparedStatement(pre);
                closeConnection(conn);
            }
        } else {
            for (String categoryId : currentCategorySubject) {
                    if (!categoryId.equals("-1"))
                    check += deteleCategorySubject(subjectId, Integer.parseInt(categoryId));
                }
        }
        
        return check;
    }
    
    @Override
    public int deteleSubjectCate(int scId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int deteleCategorySubject(int subjectId, int categoryId) throws Exception{
        Connection conn = null;
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

        String sql = "DELETE FROM dbo.CategorySubject "
                + "WHERE subjectId = ? AND "
                + "cateId = ?";
        int check = 0;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);
            pre.setInt(2, categoryId);
            check = pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return check;
    }
    /* Test DAO */
//    public static void main(String[] args) {
//        SubjectCateDAOImpl dao = new SubjectCateDAOImpl();
//        String[] category = {"2","3"};
//        try {
//            System.out.println(dao.updateSubjectContentCate(1, category ));
//        } catch (Exception ex) {
//            Logger.getLogger(SubjectCateDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    

    
}
