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

import java.util.ArrayList;
import bean.PostCate;
import dao.DBConnection;
import java.sql.PreparedStatement;
import dao.PostCateDAO;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class PostCateDAOImpl extends DBConnection implements PostCateDAO {

    @Override
    public ArrayList<PostCate> getAllPostCates() throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

        ArrayList<PostCate> allPostCate = new ArrayList();
        String sql = "SELECT * FROM [PostCate] ";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allPostCate.add(new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status")));
            }
            return allPostCate;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    @Override
    public PostCate getPostCateById(int pcId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [PostCate] WHERE postCateId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, pcId);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    @Override
    public int getBlogCateByBlogId(int blogId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [BlogCate] WHERE blogId=" + blogId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("postCateId");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
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
