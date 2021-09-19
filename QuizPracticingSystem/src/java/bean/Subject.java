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
public class Subject {
    private int subjectId, cateId;
    private boolean featuredSubject, status;
    private String subjectName, description, thumbnail;

    public Subject() {
    }

    public Subject(int subjectId, int cateId, boolean featuredSubject, boolean status, String subjectName, String description, String thumbnail) {
        this.subjectId = subjectId;
        this.cateId = cateId;
        this.featuredSubject = featuredSubject;
        this.status = status;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public boolean isFeaturedSubject() {
        return featuredSubject;
    }

    public void setFeaturedSubject(boolean featuredSubject) {
        this.featuredSubject = featuredSubject;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
}
