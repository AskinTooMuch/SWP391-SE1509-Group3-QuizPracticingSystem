/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Dimension Type entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         DuongNHHE150328      First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
*/
package bean;

/**
 *
 * @author duong
 */
public class PostCate {
    private int postCateId; /*Post category Id*/
    private String postCateName; /*Post category name*/
    private boolean status; /*Post category status*/

    /**
     * Blank constructor
     */
    public PostCate() {
    }

    /**
     * Complete constructor
     * @param postCateId
     * @param postCateName
     * @param status 
     */
    public PostCate(int postCateId, String postCateName, boolean status) {
        this.postCateId = postCateId;
        this.postCateName = postCateName;
        this.status = status;
    }

    /**
     * Get post category id
     * @return 
     */
    public int getPostCateId() {
        return postCateId;
    }

    /**
     * Get post category name
     * @return 
     */
    public String getPostCateName() {
        return postCateName;
    }

    /**
     * Get post category status
     * @return 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set post category id
     * @param postCateId 
     */
    public void setPostCateId(int postCateId) {
        this.postCateId = postCateId;
    }

    /**
     * Set post category name
     * @param postCateName 
     */
    public void setPostCateName(String postCateName) {
        this.postCateName = postCateName;
    }

    /**
     * Set post category status
     * @param status 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
