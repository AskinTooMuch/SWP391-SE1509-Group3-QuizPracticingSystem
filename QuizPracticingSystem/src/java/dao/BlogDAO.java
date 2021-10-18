/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  BlogDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
*/
package dao;

import bean.Blog;
import bean.PostCate;
import bean.User;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của BlogDAOImpl
 *
 * @author NamDH
 */
public interface BlogDAO {
    
    /**
     * Get all blog from database
     *
     * @return a list of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> getAllBlog() throws Exception;
    
    
    /**
     * Get all blog from database by blog's category
     *
     * @param postCateIdList the list of filter categories. It is an array of
     * String
     * @return a list of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> getBlogByCategory(String[] postCateIdList) throws Exception;
    
    
    /**
     * Get all blog from database by blog's author
     *
     * @param userId author's user ID. It is a <code>Integer</code>
     * @return a list of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> getBlogByUser(int userId) throws Exception;
    
    
    /**
     * Get blog from database by blog's id
     *
     * @param blogId blog's ID. It is a <code>Integer</code>
     * @return a <code>Blog</code> objects
     * @throws java.lang.Exception
     */
    public Blog getBlogById(int blogId) throws Exception;
    
    
    /**
     * Get blog from database by blog's title
     *
     * @param title blog's title. It is a <code>String</code>
     * @return a <code>Blog</code> objects
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> getBlogByTitle(String title) throws Exception;
    
    
     /**
     * Get blog from database filter by category and title
     *
     * @param postCateIdList filter blog's categories. It is a
     * <code>java.util.ArrayList</code>
     * @param search search string. It is a <code>String</code>
     * @return List of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     * 
     */
    public ArrayList<Blog> getBlogByCategoryAndTitle(String[] postCateIdList, String search) throws Exception;
    
    
    /**
     * Get all blog from database where blog's status is true
     *
     * @return List of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> getAllTrueBlog() throws Exception;
    
    
     /**
     * Edit blog information in database
     *
     * @param blogId id of the target blog. It is a <code>int</code>
     * @param blog carry edited information. It is a <code>Blog</code> object
     * @return number of changes in database. It is a <code>int</code> object
     * @throws java.lang.Exception
     */
    public int editBlog(int blogId, Blog blog) throws Exception;
    
    
    /**
     * add new blog into database
     *
     * @param blog adding target. It is a <code>Blog</code> object
     * @return number of changes in database. It is a <code>int</code> object
     * @throws java.lang.Exception
     */
    public int addBlog(Blog blog) throws Exception;
    
    
    /**
     * delete in database
     *
     * @param blogId the target blog. It is a <code>int</code>
     * @return number of changes in database. It is a <code>int</code> object
     * @throws java.lang.Exception
     */
    public int deleteBlog(int blogId) throws Exception;
    
    
    /**
     * Get 3 latest blog from database
     *
     * @return List of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> getLastBlogs() throws Exception;
    
    
    /**
     * get blog's author
     *
     * @param blogId blog target. It is a <code>int</code> object
     * @return blog's author. It is a <code>User</code> object
     * @throws java.lang.Exception
     */
    public User getAuthor(int blogId) throws Exception;
    
    
    /**
     * get blog's category
     *
     * @param blogId blog target. It is a <code>int</code> object
     * @return blog's category. It is a <code>PostCate</code> object
     * @throws java.lang.Exception
     */
    public PostCate getBlogCategory(int blogId) throws Exception;
            
    
    /**
     * divide a list of blog into many sublist(page)
     *
     * @param page blog target. It is a <code>int</code> object
     * @param list the target list. It is a <code>java.util.ArrayList</code>
     *
     * @return sublist of blog list. It is a <code>java.util.ArrayList</code>
     * @throws java.lang.Exception
     */
    public ArrayList<Blog> Paging(int page, ArrayList<Blog> list) throws Exception;
}
