/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         DuongNHHE150328  First Deploy
 */
package dao.impl;

import bean.Blog;
import java.util.ArrayList;
import bean.PostCate;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.PostCateDAO;

/**
 *
 * @author duong
 */
public class PostCateDAOImpl extends MyDAO implements PostCateDAO {

    @Override
    public ArrayList<PostCate> getAllPostCates() throws Exception {
        ArrayList<PostCate> allPostCate = new ArrayList();
        String sql = "SELECT * FROM [PostCate] ";
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allPostCate.add(new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status")));
            }
        return allPostCate;
    }

    @Override
    public PostCate getPostCateById(int pcId) throws Exception {
        String sql = "SELECT * FROM [PostCate] WHERE postCateId = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pcId);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status"));
            }
        return null;
    }

    @Override
    public int getBlogCateByBlogId(int blogId) throws Exception{
        String sql = "SELECT * FROM [BlogCate] WHERE blogId=" + blogId;
        PreparedStatement pre = conn.prepareStatement(sql);
        rs = pre.executeQuery();
        if (rs.next()) {
            return rs.getInt("postCateId");
        }
        return 0;
    }

    @Override
    public int updatePostCate(PostCate updatedPostCate) throws Exception {
        return 0;
    }

    @Override
    public int deletePostCate(int pcId) throws Exception {
        return 0;
    }

    @Override
    public int addPostCate(PostCate newPostCate) throws Exception {
        return 0;
    }

}
