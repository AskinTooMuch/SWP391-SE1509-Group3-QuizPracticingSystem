/* 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 17, 2021, 9:33:11 PM
    BlogDAO
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    17/9/21     1.0         ChucNVHE150618  First Deploy
    30/9/21     2.0         NamDHHE150519   Complete code
 */
 /*
  Lớp này có các phương thức thực hiện truy xuất và ghi dữ liệu vào database liên
  quan tới bảng Blog,CateBlog, phục vụ cho các chức năng liên quan tới Blog của 
  dự án
  @author Đinh Hải Nam
 */
package dao.impl;

import bean.Blog;
import bean.PostCate;
import bean.User;
import java.util.ArrayList;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import dao.BlogDAO;

/**
 *
 * @author ChucNVHE150618
 */
public class BlogDAOImpl extends MyDAO implements BlogDAO {

    /**
     * Get all blog from database
     *
     * @return a list of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> allBlog = new ArrayList();

        String sql = "SELECT * FROM [Blog] ORDER BY created DESC";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                allBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allBlog;
    }

    /**
     * Get all blog from database by blog's category
     *
     * @param postCateIdList the list of filter categories. It is an array of
     * String
     * @return a list of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
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
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                blogList.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return blogList;
    }

    /**
     * Get all blog from database by blog's author
     *
     * @param userId author's user ID. It is a <code>Integer</code>
     * @return a list of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Blog> getBlogByUser(int userId) {
        ArrayList<Blog> userBlog = new ArrayList();
        String sql = "SELECT * FROM Blog WHERE author =" + userId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                userBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return userBlog;
    }

    /**
     * Get blog from database by blog's id
     *
     * @param blogId blog's ID. It is a <code>Integer</code>
     * @return a <code>Blog</code> objects
     */
    @Override
    public Blog getBlogById(int blogId) {

        String sql = "SELECT * FROM Blog WHERE blogId =" + blogId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                return (new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Get blog from database by blog's title
     *
     * @param title blog's title. It is a <code>String</code>
     * @return a <code>Blog</code> objects
     */
    @Override
    public ArrayList<Blog> getBlogByTitle(String title) {
        ArrayList<Blog> titleBlog = new ArrayList();
        String sql = "SELECT * FROM [Blog] WHERE blogTitle like '%" + title + "%'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                titleBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return titleBlog;
    }

    /**
     * Get all blog from database where blog's status is true
     *
     * @return List of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Blog> getAllTrueBlog() {
        ArrayList<Blog> allTrueBlog = new ArrayList();

        String sql = "SELECT * FROM [Blog] where status = 1 ORDER BY created DESC";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                allTrueBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allTrueBlog;
    }

    /**
     * Get 3 latest blog from database
     *
     * @return List of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Blog> getLastBlogs() {
        ArrayList<Blog> lastBlog = new ArrayList();
        String sql = "SELECT TOP 3 * FROM [Blog] where status = 1 ORDER BY created DESC";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                lastBlog.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lastBlog;
    }

    /**
     * Get blog from database filter by category and title
     *
     * @param postCateIdList filter blog's categories. It is a
     * <code>java.util.ArrayList</code>
     * @param search search string. It is a <code>String</code>
     * @return List of <code>Blog</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Blog> getBlogByCategoryAndTitle(String[] postCateIdList, String search) {
        ArrayList<Blog> blogList = new ArrayList();
        String sql = "SELECT * FROM [Blog] as a join [BlogCate] as b on a.blogId = b.blogId WHERE a.status = 1";
        if (postCateIdList != null) {
            int[] cateList = new int[postCateIdList.length];
            for (int i = 0; i < postCateIdList.length; i++) {
                cateList[i] = Integer.parseInt(postCateIdList[i]);
            }
            sql += " AND b.postCateId in (";
            for (int i = 0; i < cateList.length; i++) {
                sql += cateList[i] + ",";
            }
            sql += cateList[postCateIdList.length - 1];
            sql += ")";
        }
        if (search != null) {
            sql += " AND a.blogTitle like '%" + search.toLowerCase() + "%'";
        }
        sql += " ORDER BY created DESC";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                BlogDAOImpl blogDAO = new BlogDAOImpl();
                User author = blogDAO.getAuthor(rs.getInt("blogId"));
                blogList.add(new Blog(rs.getInt("blogId"),
                        rs.getString("blogTitle"),
                        rs.getDate("created"),
                        rs.getDate("lastEdited"),
                        author,
                        rs.getString("detail"),
                        rs.getString("thumbnail"),
                        rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return blogList;
    }

    /**
     * Edit blog information in database
     *
     * @param blogId id of the target blog. It is a <code>int</code>
     * @param blog carry edited information. It is a <code>Blog</code> object
     * @return number of changes in database. It is a <code>int</code> object
     */
    @Override
    public int editBlog(int blogId, Blog blog) {
        String sql = "UPDATE [Blog] SET blogTitle =?, created =?, lastEdited =?, author =?, detail =?, thumbnail =?, status =? WHERE blogId =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setDate(2, blog.getCreated());
            ps.setDate(3, blog.getLastEdited());
            ps.setInt(4, blog.getAuthor().getUserId());
            ps.setString(5, blog.getDetail());
            ps.setString(6, blog.getThumbnail());
            ps.setInt(7, blog.getStatus() == true ? 1 : 0);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * add new blog into database
     *
     * @param blog adding target. It is a <code>Blog</code> object
     * @param sql the sql query. It is a <code>String</code>
     * @return number of changes in database. It is a <code>int</code> object
     */
    @Override
    public int addBlog(Blog blog) {
        String sql = "INSERT INTO [Blog] values(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setDate(2, blog.getCreated());
            ps.setDate(3, blog.getLastEdited());
            ps.setInt(4, blog.getAuthor().getUserId());
            ps.setString(5, blog.getDetail());
            ps.setString(6, blog.getThumbnail());
            ps.setInt(7, blog.getStatus() == true ? 1 : 0);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     * delete in database
     *
     * @param blogId the target blog. It is a <code>int</code>
     * @return number of changes in database. It is a <code>int</code> object
     */
    @Override
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

    /**
     * get blog's author
     *
     * @param blogId blog target. It is a <code>int</code> object
     * @return blog's author. It is a <code>User</code> object
     */
    @Override
    public User getAuthor(int blogId) {

        String sql = "SELECT b.userId,b.userName,b.password,b.roleId,b.profilePic,b.userMail,b.gender,b.userMobile,b.status "
                + "FROM Blog as a right join [User] as b on a.author=b.userId WHERE a.blogId=" + blogId;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getInt("roleId"),
                        rs.getString("profilePic"),
                        rs.getString("userMail"),
                        rs.getBoolean("gender"),
                        rs.getString("userMobile"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * get blog's category
     *
     * @param blogId blog target. It is a <code>int</code> object
     * @return blog's category. It is a <code>PostCate</code> object
     */
    @Override
    public PostCate getBlogCategory(int blogId) {
        String sql = "SELECT * FROM [BlogCate] as a join [PostCate] as b ON a.postCateId=b.postCateId WHERE a.blogId=" + blogId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new PostCate(rs.getInt("postCateId"),
                        rs.getString("postCateName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * divide a list of blog into many sublist(page)
     *
     * @param page blog target. It is a <code>int</code> object
     * @param list the target list. It is a <code>java.util.ArrayList</code>
     *
     * @return sublist of blog list. It is a <code>java.util.ArrayList</code>
     */
    @Override
    public ArrayList<Blog> Paging(int page, ArrayList<Blog> list) {
        //start: index of first element of the sublist
        //end: index of the last element of the sublist
        int start, end;
        int numberpage = 9;// 9 blog a sublist;
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
}
