/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Blog;
import java.util.ArrayList;
import dao.impl.MyDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ChucNVHE150618
 */
public class BlogDAO extends MyDAO {

    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> allBlog = new ArrayList();

        String sql = "SELECT * FROM [Blog]";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        rs.getInt("author"),
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allBlog;
    }

    public ArrayList<Blog> getBlogByCategory(String[] postCateIdList) {
        ArrayList<Blog> blogList = new ArrayList();
        int[] cateList = null;
        for (int i = 0; i < postCateIdList.length; i++) {
            cateList[i] = Integer.parseInt(postCateIdList[i]);
        }
        String sql = "SELECT * FROM [Blog] as a join [BlogCate] as b on a.blogId = b.blogId WHERE b.postCateId in (";
        for (int i = 0; i < cateList.length; i++) {
            sql += "," + cateList[i];
        }
        sql += ")";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                blogList.add(new Blog(rs.getInt(1),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        rs.getInt("author"),
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return blogList;
    }

    public ArrayList<Blog> Paging(int page, List<Blog> list) {
        int start, end; 
        int numberpage = 9;
        start = (page - 1) * numberpage;
        if (page * numberpage > list.size()) {
            end = list.size();
        } else {
            end = page * numberpage;
        }
        ArrayList<Blog> t = new ArrayList<>();
        for (int i = start; i < end; i++) {
            t.add(list.get(i));
        }
        return t;
       
    }


    public ArrayList<Blog> getBlogByUser(int userId) {
        ArrayList<Blog> userBlog = new ArrayList();
        String sql = "SELECT * FROM Blog WHERE author =" + userId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                userBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        rs.getInt("author"),
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userBlog;
    }

    public Blog getBlogById(int blogId) {

        String sql = "SELECT * FROM Blog WHERE blogId =" + blogId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                return new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        rs.getInt("author"),
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Blog> getBlogByTitle(String title) {
        ArrayList<Blog> titleBlog = new ArrayList();
        String sql = "SELECT * FROM [Blog] WHERE blogTitle like '%" + title + "%'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                titleBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        rs.getInt("author"),
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return titleBlog;
    }

    public int editBlog(int blogId, Blog blog) {
        String sql = "UPDATE [Blog] SET blogTitle =?, created =?, lastEdited =?, author =?, detail =?, thumbnail =?, status =? WHERE blogId =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setDate(2, blog.getCreated());
            ps.setDate(3, blog.getLastEdited());
            ps.setInt(4, blog.getAuthor());
            ps.setString(5, blog.getDetail());
            ps.setString(6, blog.getThumbnail());
            ps.setInt(7, blog.getStatus() == true ? 1 : 0);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int addBlog(Blog blog) {
        String sql = "INSERT INTO [Blog] values(?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, blog.getBlogId());
            ps.setString(2, blog.getBlogTitle());
            ps.setDate(3, blog.getCreated());
            ps.setDate(4, blog.getLastEdited());
            ps.setInt(5, blog.getAuthor());
            ps.setString(6, blog.getDetail());
            ps.setString(7, blog.getThumbnail());
            ps.setInt(8, blog.getStatus() == true ? 1 : 0);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int deleteBlog(int blogId) {
        String sql = "DELETE FROM [Blog] WHERE blogId =" + blogId;
        try {
            ps = conn.prepareStatement(sql);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

}
