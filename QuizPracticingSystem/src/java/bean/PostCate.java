/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author duong
 */
public class PostCate {
    private int postCateId;
    private String postCateName;
    private boolean status;

    public PostCate(int postCateId, String postCateName, boolean status) {
        this.postCateId = postCateId;
        this.postCateName = postCateName;
        this.status = status;
    }

    public int getPostCateId() {
        return postCateId;
    }

    public String getPostCateName() {
        return postCateName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setPostCateId(int postCateId) {
        this.postCateId = postCateId;
    }

    public void setPostCateName(String postCateName) {
        this.postCateName = postCateName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
