/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Subject entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  27/9/21     1.0         ChucNVHE150618  First Deploy
*/
package bean;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Subject {
    private int subjectId;  /* Subject's id */
    private String subjectName; /* Subject's Name */
    private String description; /* Subject's Description */
    private String thumbnail;   /* Subject's Thumbnail */
    private boolean featuredSubject;    /* Is featuredSubject or not */
    private boolean status; /* Subject's Status */
    private ArrayList<Dimension> dimensions;    /* Subject's dimensions */
    private ArrayList<SubjectCate> categories;  /* Subject's categories */

    public Subject() {
    }
    
    /* Constructor without arraylists */
    public Subject(int subjectId, String subjectName, String description, String thumbnail, boolean featuredSubject, boolean status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.featuredSubject = featuredSubject;
        this.status = status;
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
    
    @Override
    public String toString(){
        return ("Id: " + this.subjectId +
                "\nName: " + this.subjectName +
                "\nDescription: " + this.description + 
                "\nThumbnail: " + this.thumbnail + 
                "\nFeatured: " + featuredSubject + 
                "\nStatus: " + status +
                "\nCategory: " + categories.size() +
                "\nDimension: " + dimensions.size());
    }
    
}
