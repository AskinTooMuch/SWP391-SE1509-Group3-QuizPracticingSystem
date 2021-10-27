/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Lesson Type entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
*/
package bean;

/**
 * LessonType entity
 *
 * @author admin
 */
public class LessonType {
    private int lessonTypeId; /*Lesson type id*/
    private String lessonTypeName; /*Lesson type name*/
    private boolean status; /*Lesson Status*/

    /**
     * Blank constructor
     */
    public LessonType() {
    }
    
    /**
     * Complete constructor
     * @param lessonTypeId
     * @param lessonTypeName
     * @param status 
     */
    public LessonType(int lessonTypeId, String lessonTypeName, boolean status) {
        this.lessonTypeId = lessonTypeId;
        this.lessonTypeName = lessonTypeName;
        this.status = status;
    }

    /**
     * Get lesson type Id
     * @return 
     */
    public int getLessonTypeId() {
        return lessonTypeId;
    }

    /**
     * Set lesson Type ID
     * @return 
     */
    public String getLessonTypeName() {
        return lessonTypeName;
    }

    /**
     * Get lesson type status
     * @return 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set lesson type Id
     * @param lessonTypeId 
     */
    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    /**
     * Set lesson type name
     * @param lessonTypeName 
     */
    public void setLessonTypeName(String lessonTypeName) {
        this.lessonTypeName = lessonTypeName;
    }

    /**
     * Set lesson type status
     * @param status 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
