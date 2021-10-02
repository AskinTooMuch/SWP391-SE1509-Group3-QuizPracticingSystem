/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Blog;
import bean.PostCate;
import bean.User;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface BlogDAO {
    public ArrayList<Blog> getAllBlog() throws Exception;
    
    public ArrayList<Blog> getBlogByCategory(String[] postCateIdList) throws Exception;
    
    public ArrayList<Blog> getBlogByUser(int userId) throws Exception;
    
    public Blog getBlogById(int blogId) throws Exception;
    
    public ArrayList<Blog> getBlogByTitle(String title) throws Exception;
    
    public ArrayList<Blog> getBlogByCategoryAndTitle(String[] postCateIdList, String search) throws Exception;
    
    public ArrayList<Blog> getAllTrueBlog() throws Exception;
    
    public int editBlog(int blogId, Blog blog) throws Exception;
    
    public int addBlog(Blog blog) throws Exception;
    
    public int deleteBlog(int blogId) throws Exception;
    
    public ArrayList<Blog> getLastBlogs() throws Exception;
    
    public User getAuthor(int blogId) throws Exception;
    
    public PostCate getBlogCategory(int blogId) throws Exception;
            
    public ArrayList<Blog> Paging(int page, ArrayList<Blog> list) throws Exception;
}
