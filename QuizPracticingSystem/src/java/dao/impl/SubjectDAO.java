/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Subject;
import dao.DimensionINT;
import dao.MyDAO;
import dao.SubjectCateINT;
import dao.SubjectINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class SubjectDAO extends MyDAO implements SubjectINT{
    @Override
    /* Get all subject in the Subject table */
    public ArrayList<Subject> getAllSubjects(){
        ArrayList<Subject> allSubject = new ArrayList();
        DimensionINT dimensionDAO = new DimensionDAO();
        SubjectCateINT subjectCateDAO = new SubjectCateDAO();
        
        String sqlSubject = "SELECT * FROM [Subject]";
        
        try {
            /* Get the subject */
            PreparedStatement preSubject = conn.prepareStatement(sqlSubject);
            rs = preSubject.executeQuery();
            while (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featuredSubject = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");
                
                allSubject.add(new Subject(subjectId, subjectName, description, 
                        thumbnail, featuredSubject, status, 
                        dimensionDAO.getDimensionBySubject(subjectId), 
                        subjectCateDAO.getSubjectCateBySubject(subjectId)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allSubject;
    }
    
    @Override
    public Subject getSubjectbyId(int subjectId){
        Subject subjectById = null;
        DimensionINT dimensionDAO = new DimensionDAO();
        SubjectCateINT subjectCateDAO = new SubjectCateDAO();
        
        String sqlSubject = "SELECT * FROM [Subject] WHERE [subjectId] = "+subjectId;
        
        try {
            /* Get the subject */
            PreparedStatement preSubject = conn.prepareStatement(sqlSubject);
            rs = preSubject.executeQuery();
            if (rs.next()) {
                int subjectIdResult = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featuredSubject = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");
                
                subjectById = new Subject(subjectId, subjectName, description, 
                        thumbnail, featuredSubject, status, 
                        dimensionDAO.getDimensionBySubject(subjectId), 
                        subjectCateDAO.getSubjectCateBySubject(subjectId));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return subjectById;
    }
    
    @Override
    public ArrayList<Subject> getSubjectbyCateId(int cateId){
        ArrayList<Subject> subjectByCate = new ArrayList();

        String sql = "SELECT S.[subjectId]\n" +
                    "  FROM [QuizSystem].[dbo].[Subject] S\n" +
                    "  INNER JOIN [QuizSystem].[dbo].CategorySubject CS ON S.subjectId = CS.subjectId\n" +
                    "  WHERE CS.cateId =" + cateId;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                subjectByCate.add(getSubjectbyId(rs.getInt("subjectId")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return subjectByCate;
    }
    
    @Override
    public int updateSubject(int subjectId, Subject subject){
        int i = 0;
        return i;
    }
    
    @Override
    public int addSubject(Subject subject){
        int i = 0;
        return i;
    }
    
    @Override
    public int deleteSubject(int subjectId){
        int i = 0;
        return i;
    }
    
    /* Test DAO */
//    public static void main(String[] args) {
//        SubjectDAO dao = new SubjectDAO();
//        ArrayList<Subject> subjectByCate = dao.getAllSubjects();
//        for (Subject subject : subjectByCate) {
//            if (subject==null) System.out.println("null");
//            else {
//                System.out.println(subject.toString());
//            }
//        }
//    }
}
