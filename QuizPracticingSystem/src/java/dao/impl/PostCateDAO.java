/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Blog;
import java.util.ArrayList;
import bean.PostCate;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author duong
 */
public class PostCateDAO extends MyDAO{
    public ArrayList<PostCate> getAllPostCates(){
        ArrayList<PostCate> allPostCate = new ArrayList();
        String sql = "SELECT * FROM [PostCate] ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allPostCate.add(new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allPostCate;
    }
    
    
    public PostCate getPostCateById(int pcId){
        String sql = "SELECT * FROM Blog [PostCate] WHERE postCateId =" + pcId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public int updatePostCate(PostCate updatedPostCate){
        return 0;
    }
    
    public int deletePostCate(int pcId){
        return 0;
    }
    
    public int addPostCate(PostCate newPostCate){
        return 0;
    }
}
