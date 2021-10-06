/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Subject;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface SubjectDAO {
    public ArrayList<Subject> getAllSubjects() throws Exception;
    
    public ArrayList<Subject> getFeaturedSubjects() throws Exception;
    
    public ArrayList<Subject> getSubjectsAssigned(int userId) throws Exception;
    
    public Subject getSubjectbyId(int subjectId) throws Exception;
    
    public ArrayList<Subject> getSubjectbyCateId(int cateId) throws Exception;
    
    public int updateSubject(int subjectId, Subject subject) throws Exception;
    
    public int updateSubjectBasic(int subjectId, Subject subject) throws Exception;
    
    public int addSubject(Subject subject) throws Exception;
    
    public int deleteSubject(int subjectId) throws Exception;
}
