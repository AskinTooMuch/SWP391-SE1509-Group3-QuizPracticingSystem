/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Subject entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  27/9/21     1.0         ChucNVHE150618  First Deploy
 *  16/10/21    1.0         ChucNVHE150618  Comment
*/
package bean;

/**
 *
 * @author admin
 */
public class SubjectCate {
    private int subjectCateId; /*Subject category id*/
    private String subjectCateName; /*Subject category name*/
    private boolean status; /*Subject category status*/

    /**
     * Blank Constructor
     */
    public SubjectCate() {
    }

    /**
     * Complete constructor
     * @param subjectCateId
     * @param subjectCateName
     * @param status 
     */
    public SubjectCate(int subjectCateId, String subjectCateName, boolean status) {
        this.subjectCateId = subjectCateId;
        this.status = status;
        this.subjectCateName = subjectCateName;
    }

    
    /**
     * Get subject category id
     * @return 
     */
    public int getSubjectCateId() {
        return subjectCateId;
    }

    /**
     * Set subject category id
     * @param subjectCateId 
     */
    public void setSubjectCateId(int subjectCateId) {
        this.subjectCateId = subjectCateId;
    }

    /**
     * Get status
     * @return 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set status
     * @param status 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Get subject category name
     * @return 
     */
    public String getSubjectCateName() {
        return subjectCateName;
    }

    /**
     * Set subject category name
     * @param subjectCateName 
     */
    public void setSubjectCateName(String subjectCateName) {
        this.subjectCateName = subjectCateName;
    }
    
}
