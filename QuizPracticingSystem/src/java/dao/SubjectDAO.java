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
 * The class has methods needed for initialize connection with database and 
 * execute queries with Subject and associate tables
 * @author NamDH
 */
public interface SubjectDAO {
    /**
     *  Get all available subject in the Subject table (status = 1)
     * @return @throws Exception 
     */
    public ArrayList<Subject> getAllSubjects() throws Exception;
    
    /**
     * Get available subject paginated
     * @param page
     * @return
     * @throws Exception 
     */
    public ArrayList<Subject> getSubjectsPaging(int page) throws Exception;
    
    /**
     *  Get all subject in the Subject table
     * @return @throws Exception 
     */
    public ArrayList<Subject> getTrueAllSubjects() throws Exception;
    
    /**
     * Get all subject paginated
     * @param page
     * @return
     * @throws Exception 
     */
    public ArrayList<Subject> getTrueSubjectsPaging(int page) throws Exception;
    
    /**
     *
     * @return @throws Exception Get featured subjects
     */
    public ArrayList<Subject> getFeaturedSubjects() throws Exception;
    
    /**
     *
     * @param userId
     * @return
     * @throws Exception Get subjects assigned by certain expert
     */
    public ArrayList<Subject> getSubjectsAssigned(int userId) throws Exception;
    
    /**
     * Get All subject assigned to an expert paginated
     * @param userId
     * @param page
     * @return
     * @throws Exception 
     */
    public ArrayList<Subject> getSubjectsAssignedPaging(int userId, int page) throws Exception;
    
    /**
     *
     * @param subjectId
     * @return
     * @throws Exception Get subject with a certain Id
     */
    public Subject getSubjectbyId(int subjectId) throws Exception;
    
    /**
     *
     * @param cateId
     * @return
     * @throws Exception Get the subject list that has a certain category id
     */
    public ArrayList<Subject> getSubjectbyCateId(int cateId) throws Exception;
    
    /**
     * Update subject with certain id
     * @param subjectId
     * @param subject
     * @return
     * @throws Exception 
     */
    public int updateSubject(int subjectId, Subject subject) throws Exception;
    
    /**
     *  Method to perform the single-value parameters of subject
     * @param subjectId
     * @param subject
     * @return
     * @throws Exception 
     */
    public int updateSubjectBasic(int subjectId, Subject subject) throws Exception;
    
    /**
     *
     * @return @throws Exception Get 5 las added subject in the Subject table
     */
    public ArrayList<Subject> get5LastAddedSubject() throws Exception;
    
    /**
     * Add new subject into database
     * @param newSubject
     * @return
     * @throws Exception 
     */
    public int addSubject(Subject subject) throws Exception;
    
    /**
     * Delete subject with a certain id
     * @param subjectId
     * @return
     * @throws Exception 
     */
    public int deleteSubject(int subjectId) throws Exception;
}
