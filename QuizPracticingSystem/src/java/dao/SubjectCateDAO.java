/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public ArrayList<SubjectCate> getRemainSubjectCateBySubject(int subjectId) throws Exception;
    
    public int updateSubjectCate(SubjectCate updatedSubjectCate) throws Exception;
    
    public int deteleSubjectCate(int scId) throws Exception;
}
