/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.SubjectCate;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.SubjectCateDAO;

/**
 *
 * @author admin
 */
public class SubjectCateDAOImpl extends MyDAO implements SubjectCateDAO{

    @Override
    public ArrayList<SubjectCate> getAllSubjectCates() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubjectCate getSubjectCateById(int scId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SubjectCate> getSubjectCateBySubject(int subjectId) throws Exception {
    /* Getcategory list of the subject */
        ArrayList<SubjectCate> categories = new ArrayList<>();
        String sql = "SELECT C.[subjectId]\n" +
                    "      ,C.[cateId]\n" +
                    "	   ,S.[status]\n" +
                    "	   ,S.subjectCateName\n" +
                    "  FROM [QuizSystem].[dbo].[CategorySubject] C \n" +
                    "  INNER JOIN [QuizSystem].[dbo].SubjectCate S\n" +
                    "  ON C.cateId = S.subjectCateId WHERE C.subjectId =" + subjectId;
        PreparedStatement pre = conn.prepareStatement(sql);
        rs = pre.executeQuery();
        while (rs.next()) {
            categories.add(new SubjectCate(rs.getInt("cateId"), rs.getString("subjectCateName"), rs.getBoolean("status")));
        }
        return categories;
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
//    public static void main(String[] args) {
//        SubjectCateDAOImpl dao = new SubjectCateDAOImpl();
//        System.out.println(dao.getSubjectCateBySubject(3).size());
//    }
}
