/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;

/**
 *
 * @author ChucNVHE150618
 */
public class Blog {

    private int blogId;
    private String blogTitle;
    private Date created;
    private Date lastEdited;
    private User author;    
    private String detail;
    private String thumbnail;
    private Boolean status;

    public Blog() {
    }

    public Blog(int blogId, String blogTitle, Date created, Date lastEdited, User author, String detail, String thumbnail, Boolean status) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.created = created;
        this.lastEdited = lastEdited;
        this.author = author;
        this.detail = detail;
        this.thumbnail = thumbnail;
        this.status = status;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

   
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
