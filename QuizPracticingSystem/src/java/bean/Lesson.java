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
public class Lesson {
    private int lessonId, subjectId, lessonTypeId;
    private String lessonName,lessonTypeName, lessonOrder,videoLink,content;
    private boolean status;

    public Lesson() {
    }

    public Lesson(int lessonId, int subjectId, int lessonTypeId, String lessonName, String lessonTypeName, String lessonOrder, String videoLink, String content, boolean status) {
        this.lessonId = lessonId;
        this.subjectId = subjectId;
        this.lessonTypeId = lessonTypeId;
        this.lessonName = lessonName;
        this.lessonTypeName = lessonTypeName;
        this.lessonOrder = lessonOrder;
        this.videoLink = videoLink;
        this.content = content;
        this.status = status;
    }

    public String getLessonTypeName() {
        return lessonTypeName;
    }

    public void setLessonTypeName(String lessonTypeName) {
        this.lessonTypeName = lessonTypeName;
    }


    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(String lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
