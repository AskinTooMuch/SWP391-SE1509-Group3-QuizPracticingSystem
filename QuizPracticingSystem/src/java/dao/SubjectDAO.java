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
    public ArrayList<Subject> getAllSubjects();
    
    public ArrayList<Subject> getFeaturedSubjects();
    
    public ArrayList<Subject> getSubjectsAssigned(int userId);
    
    public Subject getSubjectbyId(int subjectId);
    
    public ArrayList<Subject> getSubjectbyCateId(int cateId);
    
    public int updateSubject(int subjectId, Subject subject);
    
    public int addSubject(Subject subject);
    
    public int deleteSubject(int subjectId);
}
