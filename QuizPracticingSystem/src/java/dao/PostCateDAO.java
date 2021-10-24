/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  PostCateDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 *  24/10/21    1.2         DuongNHHE150328     Add method
*/
package dao;

import bean.PostCate;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của PostCateDAOImpl
 *
 * @author NamDH
 */
public interface PostCateDAO {

    /**
     * get all psot categories where status = 1
     * @return
     * @throws Exception 
     */
    public ArrayList<PostCate> getAllPostCates() throws Exception;

    /**
     * get post categoory by id
     * @param postCateId
     * @return
     * @throws Exception 
     */
    public PostCate getPostCateById(int postCateId) throws Exception;

    /**
     * update a existed post category in the database
     * @param updatedPostCate
     * @return
     * @throws Exception 
     */
    public int updatePostCate(PostCate updatedPostCate) throws Exception;

    /**
     * get blog category id by blog id
     * @param blogId
     * @return
     * @throws Exception 
     */
    public int getBlogCateByBlogId(int blogId) throws Exception;

    /**
     * delete a post category from database
     * @param postCateId
     * @return
     * @throws Exception 
     */
    public int deletePostCate(int postCateId) throws Exception;

    /**
     * add new post category to database
     * @param newPostCate
     * @return
     * @throws Exception 
     */
    public int addPostCate(PostCate newPostCate) throws Exception;
    
    /**
     * get all psot categories
     * @return
     * @throws Exception 
     */
    public ArrayList<PostCate> getAllStatusPostCates() throws Exception;
}
