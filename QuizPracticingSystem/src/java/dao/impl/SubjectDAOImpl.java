/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         ChucNVHE150618  First Deploy
 *  24/9/21     1.0         ChucNVHE150618  Add methods: getAllSubjects, getSubjectById, getSubjectByCateId, update-add-delete subject
 *  27/9/21     1.0         ChucNVHE150618  Add methods: getFeaturedSubjects, getAssignedSubject
*/
package dao.impl;

import bean.Subject;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DimensionDAO;
import dao.SubjectCateDAO;
import dao.SubjectDAO;

/**
 *
 * @author admin
 */
public class SubjectDAOImpl extends MyDAO implements SubjectDAO{
    @Override
    /* Get all subject in the Subject table */
    public ArrayList<Subject> getAllSubjects(){
        ArrayList<Subject> allSubject = new ArrayList();
        DimensionDAO dimensionDAO = new DimensionDAOImpl();
        SubjectCateDAO subjectCateDAO = new SubjectCateDAOImpl();
        
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
                Boolean featured = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");
                
                allSubject.add(new Subject(subjectId, subjectName, description, 
                        thumbnail, featured, status, 
                        dimensionDAO.getDimensionBySubject(subjectId), 
                        subjectCateDAO.getSubjectCateBySubject(subjectId)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allSubject;
    }
    
    @Override
    public ArrayList<Subject> getFeaturedSubjects() {
        ArrayList<Subject> featuredSubjects = new ArrayList();
        DimensionDAO dimensionDAO = new DimensionDAOImpl();
        SubjectCateDAO subjectCateDAO = new SubjectCateDAOImpl();
        
        String sqlSubject = "SELECT * FROM [Subject] WHERE featuredSubject = 1";
        
        try {
            /* Get the subject */
            PreparedStatement preSubject = conn.prepareStatement(sqlSubject);
            rs = preSubject.executeQuery();
            while (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featured = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");
                
                featuredSubjects.add(new Subject(subjectId, subjectName, description, 
                        thumbnail, featured, status, 
                        dimensionDAO.getDimensionBySubject(subjectId), 
                        subjectCateDAO.getSubjectCateBySubject(subjectId)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return featuredSubjects;
    }
    /* Get subjects assigned by certain expert */
    @Override
    public ArrayList<Subject> getSubjectsAssigned(int userId) {
        ArrayList<Subject> assignedSubjects = new ArrayList();
        DimensionDAO dimensionDAO = new DimensionDAOImpl();
        SubjectCateDAO subjectCateDAO = new SubjectCateDAOImpl();
        
        String sqlSubject = "SELECT S.[subjectId],[subjectName],[description],[thumbnail],[featuredSubject],S.[status],SE.[userId]\n" +
                            "  FROM [QuizSystem].[dbo].[Subject] S INNER JOIN [QuizSystem].dbo.[SubjectExpert] SE\n" +
                            "  ON S.subjectId = SE.subjectId WHERE SE.userId = " + userId;
        
        try {
            /* Get the subject */
            PreparedStatement preSubject = conn.prepareStatement(sqlSubject);
            rs = preSubject.executeQuery();
            while (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featured = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");
                
                assignedSubjects.add(new Subject(subjectId, subjectName, description, 
                        thumbnail, featured, status, 
                        dimensionDAO.getDimensionBySubject(subjectId), 
                        subjectCateDAO.getSubjectCateBySubject(subjectId)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return assignedSubjects;
    }
    
    @Override
    public Subject getSubjectbyId(int subjectId){
        Subject subjectById = null;
        DimensionDAO dimensionDAO = new DimensionDAOImpl();
        SubjectCateDAO subjectCateDAO = new SubjectCateDAOImpl();
        
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
//        SubjectDAOImpl dao = new SubjectDAOImpl();
//        ArrayList<Subject> subjectByCate = dao.getSubjectsAssigned(7);
//        for (Subject subject : subjectByCate) {
//            if (subject==null) System.out.println("null");
//            else {
//                System.out.println(subject.toString());
//            }
//        }
//    }

}
