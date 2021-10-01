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
    public ArrayList<Blog> getAllBlog();
    
    public ArrayList<Blog> getBlogByCategory(String[] postCateIdList);
    
    public ArrayList<Blog> getBlogByUser(int userId);
    
    public Blog getBlogById(int blogId);
    
    public ArrayList<Blog> getBlogByTitle(String title);
    
    public ArrayList<Blog> getBlogByCategoryAndTitle(String[] postCateIdList, String search);
    
    public ArrayList<Blog> getAllTrueBlog();
    
    public int editBlog(int blogId, Blog blog);
    
    public int addBlog(Blog blog);
    
    public int deleteBlog(int blogId);
    
    public ArrayList<Blog> getLastBlogs();
    
    public User getAuthor(int blogId);
    
    public PostCate getBlogCategory(int blogId);
            
    public ArrayList<Blog> Paging(int page, ArrayList<Blog> list);
}
