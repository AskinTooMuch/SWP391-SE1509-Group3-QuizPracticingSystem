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
public class LessonType {
    private int lessonTypeId;
    private String lessonTypeName;
    private boolean status;

    public LessonType(int lessonTypeId, String lessonTypeName, boolean status) {
        this.lessonTypeId = lessonTypeId;
        this.lessonTypeName = lessonTypeName;
        this.status = status;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public String getLessonTypeName() {
        return lessonTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public void setLessonTypeName(String lessonTypeName) {
        this.lessonTypeName = lessonTypeName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
