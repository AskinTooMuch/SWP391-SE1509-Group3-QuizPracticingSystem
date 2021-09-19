/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author admin
 */
public class SubjectCate {
    private int subjectCateId;
    private boolean status;
    private String subjectCateName;

    public SubjectCate() {
    }

    public SubjectCate(int subjectCateId, boolean status, String subjectCateName) {
        this.subjectCateId = subjectCateId;
        this.status = status;
        this.subjectCateName = subjectCateName;
    }

    public int getSubjectCateId() {
        return subjectCateId;
    }

    public void setSubjectCateId(int subjectCateId) {
        this.subjectCateId = subjectCateId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSubjectCateName() {
        return subjectCateName;
    }

    public void setSubjectCateName(String subjectCateName) {
        this.subjectCateName = subjectCateName;
    }
    
}
