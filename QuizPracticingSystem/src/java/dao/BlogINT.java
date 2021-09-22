/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Blog;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface BlogINT {
    public ArrayList<Blog> getAllBlog();
    
    public ArrayList<Blog> getBlogByCategory(String[] postCateIdList);
    
    public ArrayList<Blog> getBlogByUser(int userId);
    
    public Blog getBlogById(int blogId);
    
    public ArrayList<Blog> getBlogByTitle(String title);
    
    public ArrayList<Blog> getBlogByCategoryAndTitle(String[] postCateIdList, String search);
    
    public int editBlog(int blogId, Blog blog);
    
    public int addBlog(Blog blog);
    
    public int deleteBlog(int blogId);
    
    public ArrayList<Blog> Paging(int page, ArrayList<Blog> list);
}
