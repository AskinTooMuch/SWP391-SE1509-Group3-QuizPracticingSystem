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
    private int subjectId;
    private String subjectName;
    private int cateId;
    private String description;
    private String thumbnail;
    private boolean featuredSubject;
    private boolean status;
    

    public Subject() {
    }

    public Subject(int subjectId, String subjectName, int cateId, String description, String thumbnail, boolean featuredSubject, boolean status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.cateId = cateId;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
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
