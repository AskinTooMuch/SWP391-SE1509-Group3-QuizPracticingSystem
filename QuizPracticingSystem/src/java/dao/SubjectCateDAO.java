/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SubjectCateDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
*/
package dao;

import bean.SubjectCate;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface SubjectCateDAO {
    public ArrayList<SubjectCate> getAllSubjectCates() throws Exception;
    
    public SubjectCate getSubjectCateById(int scId) throws Exception;
    
    public ArrayList<SubjectCate> getSubjectCateBySubject(int subjectId) throws Exception;
    
    public String[] getSubjectCateIdBySubject(int subjectId) throws Exception;
    
    public ArrayList<SubjectCate> getRemainSubjectCateBySubject(int subjectId) throws Exception;
    
    public int addSubjectCate(SubjectCate updatedSubjectCate) throws Exception;
    
    public int addCategorySubject(int subjectId, int categoryId) throws Exception;
    
    public int updateSubjectContentCate(int subjectCategoryId, String[] updatedSubjectCateId) throws Exception;
    
    public int updateSubjectCate(int subjectCategoryId, SubjectCate updatedSubjectCate) throws Exception;
    
    public int deteleSubjectCate(int scId) throws Exception;
    
    public int deteleCategorySubject(int subjectId, int categoryId) throws Exception;
}
