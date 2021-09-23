/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Subject {
    private int subjectId;
    private String subjectName;
    private String description;
    private String thumbnail;
    private boolean featuredSubject;
    private boolean status;
    private ArrayList<Dimension> dimensions;
    private ArrayList<SubjectCate> categories; 

    public Subject() {
    }

    public Subject(int subjectId, String subjectName, String description, String thumbnail, boolean featuredSubject, boolean status, ArrayList<Dimension> dimensions,  ArrayList<SubjectCate> categories) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
        this.dimensions = dimensions;
        this.categories = categories;
    }

    public ArrayList<SubjectCate> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<SubjectCate> categories) {
        this.categories = categories;
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(ArrayList<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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
    
    public String toString(){
        return ("Id: " + this.subjectId +
                "/nName: " + this.subjectName +
                "/nDescription: " + this.description + 
                "/nThumbnail: " + this.thumbnail + 
                "/nFeatured: " + featuredSubject + 
                "/nStatus: " + status +
                "/nCategory: " + categories.size() +
                "/nDimension: " + dimensions.size());
    }
    
}
