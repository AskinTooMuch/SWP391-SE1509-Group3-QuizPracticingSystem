/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SubjectDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.Subject;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của AnswerDAOImpl
 *
 * @author NamDH
 */
public interface SubjectDAO {
    public ArrayList<Subject> getAllSubjects() throws Exception;
    
    public ArrayList<Subject> getSubjectsPaging(int page) throws Exception;
    
    public ArrayList<Subject> getTrueAllSubjects() throws Exception;
    
    public ArrayList<Subject> getTrueSubjectsPaging(int page) throws Exception;
    
    public ArrayList<Subject> getFeaturedSubjects() throws Exception;
    
    public ArrayList<Subject> getSubjectsAssigned(int userId) throws Exception;
    
    public Subject getSubjectbyId(int subjectId) throws Exception;
    
    public ArrayList<Subject> getSubjectbyCateId(int cateId) throws Exception;
    
    public int updateSubject(int subjectId, Subject subject) throws Exception;
    
    public int updateSubjectBasic(int subjectId, Subject subject) throws Exception;
    
    public ArrayList<Subject> get5LastAddedSubject() throws Exception;
    
    public int addSubject(Subject subject) throws Exception;
    
    public int deleteSubject(int subjectId) throws Exception;
}
