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
import dao.BlogINT;
import java.util.ArrayList;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ChucNVHE150618
 */
public class BlogDAO extends MyDAO implements BlogINT {

    @Override
    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> allBlog = new ArrayList();

        String sql = "SELECT * FROM [Blog] ORDER BY created DESC";

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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public ArrayList<Blog> getAllTrueBlog() {
        ArrayList<Blog> allTrueBlog = new ArrayList();

        String sql = "SELECT * FROM [Blog] where status = 1 ORDER BY created DESC";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                allTrueBlog.add(new Blog(rs.getInt("blogId"),
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
        return allTrueBlog;
    }
    
@Override
    public ArrayList<Blog> getLastBlogs() {
        ArrayList<Blog> lastBlog = new ArrayList();
        String sql = "SELECT TOP 3 * FROM [Blog] where status = 1 ORDER BY created DESC";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                lastBlog.add(new Blog(rs.getInt("blogId"),
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
        return lastBlog;
    }

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

    @Override
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

    @Override
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

    @Override
    public ArrayList<Blog> Paging(int page, ArrayList<Blog> list) {
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

    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
        String[] a = {"1", "2"};
        List<Blog> list = dao.getLastBlogs();
        for (Blog o : list) {
            System.out.println(o);
        }
    }

}
