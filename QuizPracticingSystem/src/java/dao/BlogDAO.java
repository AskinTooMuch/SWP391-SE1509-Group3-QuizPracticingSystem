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
public class BlogDAO {
    
    public ArrayList<Blog> getAllBlog(){
        ArrayList<Blog> allBlog = null;
        
        return allBlog;
    }
    
    public ArrayList<Blog> getBlogByUser(int userId){
        ArrayList<Blog> userBlog = null;
        
        return userBlog;
    }
    
    public Blog getBlogById(int blogId){
        Blog blog = null;
        
        return blog;
    }
    
    public ArrayList<Blog> getBlogByTitle(String title){
        ArrayList<Blog> titleBlog = null;
        
        return titleBlog;
    }
    
    public int editBlog(int blogId, Blog blog){
        int i = 0;
        
        return i;
    }
    
    public int addBlog(Blog blog){
        int i = 0;
        
        return i;
    }
    
    public int deleteBlog(int blogId){
        int i = 0;
        
        return i;
    }
}
