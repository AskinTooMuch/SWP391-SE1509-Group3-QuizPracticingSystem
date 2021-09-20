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
    private int lessonId;
    private int subjectId;
    private String lessonName;
    private int lessonOrder;
    private int lessonTypeId;
    private String videoLink;
    private String content;
    private boolean status;
    private String lessonTypeName;

    public Lesson() {
    }

    public Lesson(int lessonId, int subjectId, String lessonName, int lessonOrder, int lessonTypeId, String videoLink, String content, boolean status, String lessonTypeName) {
        this.lessonId = lessonId;
        this.subjectId = subjectId;
        this.lessonName = lessonName;
        this.lessonOrder = lessonOrder;
        this.lessonTypeId = lessonTypeId;
        this.videoLink = videoLink;
        this.content = content;
        this.status = status;
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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public int getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(int lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
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

    public String getLessonTypeName() {
        return lessonTypeName;
    }

    public void setLessonTypeName(String lessonTypeName) {
        this.lessonTypeName = lessonTypeName;
    }

    
}
