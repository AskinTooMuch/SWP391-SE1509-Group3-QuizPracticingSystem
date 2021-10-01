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
    public ArrayList<SubjectCate> getAllSubjectCates();
    
    public SubjectCate getSubjectCateById(int scId);
    
    public ArrayList<SubjectCate> getSubjectCateBySubject(int subjectId);
    
    public int updateSubjectCate(SubjectCate updatedSubjectCate);
    
    public int deteleSubjectCate(int scId);
}
