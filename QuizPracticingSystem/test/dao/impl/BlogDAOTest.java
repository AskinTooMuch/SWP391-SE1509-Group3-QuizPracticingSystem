/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Blog;
import bean.PostCate;
import bean.User;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class BlogDAOTest {
    
    public BlogDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllBlog method, of class BlogDAO.
     */
    @Test
    public void testGetAllBlog() throws Exception {
        System.out.println("getAllBlog");
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 0;
        int result = instance.getAllBlog().size();
        assertTrue(expResult < result);
    }

    /**
     * Test of getBlogByCategory method, of class BlogDAO.
     */
    @Test
    public void testGetBlogByCategory() throws Exception {
        System.out.println("getBlogByCategory");
        String[] postCateIdList = {"1","2"};
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 0;
        int result = instance.getBlogByCategory(postCateIdList).size();
        assertTrue(expResult < result);
    }

    /**
     * Test of getBlogByUser method, of class BlogDAO.
     */
    @Test
    public void testGetBlogByUser() throws Exception {
        System.out.println("getBlogByUser");
        int userId = 2;
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 4;
        int result = instance.getBlogByUser(userId).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBlogById method, of class BlogDAO.
     */
    @Test
    public void testGetBlogById() throws Exception {
        System.out.println("getBlogById");
        int blogId = 1;
        BlogDAOImpl instance = new BlogDAOImpl();
        String expResult = "Rita’s Way: Why is it so Effective?";
        String result = instance.getBlogById(blogId).getBlogTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBlogByTitle method, of class BlogDAO.
     */
    @Test
    public void testGetBlogByTitle() throws Exception {
        System.out.println("getBlogByTitle");
        String title = "Why";
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 0;
        int result = instance.getBlogByTitle(title).size();
        assertTrue(expResult < result);
    }

    /**
     * Test of getAllTrueBlog method, of class BlogDAO.
     */
    @Test
    public void testGetAllTrueBlog() throws Exception {
        System.out.println("getAllTrueBlog");
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 0;
        int result = instance.getAllTrueBlog().size();
        assertTrue(expResult < result);
    }

    /**
     * Test of getLastBlogs method, of class BlogDAO.
     */
    @Test
    public void testGetLastBlogs() throws Exception {
        System.out.println("getLastBlogs");
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResultUpper = 3;
        int expResultLower = 0;
        int result = instance.getLastBlogs().size();
        assertTrue((expResultUpper >= result) && (expResultLower <= result));
    }

    /**
     * Test of getBlogByCategoryAndTitle method, of class BlogDAO.
     */
    @Test
    public void testGetBlogByCategoryAndTitle() throws Exception {
        System.out.println("getBlogByCategoryAndTitle");
        String[] postCateIdList = null;
        String search = "";
        BlogDAOImpl instance = new BlogDAOImpl();
        ArrayList<Blog> expResult = null;
        ArrayList<Blog> result = instance.getBlogByCategoryAndTitle(postCateIdList, search);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
         * Test of addBlog method, of class BlogDAO.
         */
        @Test
        public void testAddBlog() throws Exception {
            System.out.println("addBlog");
            User user = new User(1, "testUserName", "testUserPassword", 1, "testProfilePic", "testUserMail", true, "0303030303", true);
            Blog blog = new Blog(0, "testBlogTitle", Date.valueOf("2020-12-12"), Date.valueOf("2020-12-12"), user, "test", "testThumbnail", true);
            
            BlogDAOImpl instance = new BlogDAOImpl();
            int expResult = 1;
            int result = instance.addBlog(blog);
            assertEquals(expResult, result);
        }
        
    /**
     * Test of editBlog method, of class BlogDAO.
     */
    @Test
    public void testEditBlog() throws Exception {
        System.out.println("editBlog");
        int blogId = 0;
        Blog blog = null;
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 0;
        int result = instance.editBlog(blogId, blog);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    

    /**
     * Test of deleteBlog method, of class BlogDAO.
     */
    @Test
    public void testDeleteBlog() throws Exception {
        System.out.println("deleteBlog");
        int blogId = 0;
        BlogDAOImpl instance = new BlogDAOImpl();
        int expResult = 0;
        int result = instance.deleteBlog(blogId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthor method, of class BlogDAO.
     */
    @Test
    public void testGetAuthor() throws Exception {
        System.out.println("getAuthor");
        int blogId = 0;
        BlogDAOImpl instance = new BlogDAOImpl();
        User expResult = null;
        User result = instance.getAuthor(blogId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBlogCategory method, of class BlogDAO.
     */
    @Test
    public void testGetBlogCategory() throws Exception {
        System.out.println("getBlogCategory");
        int blogId = 0;
        BlogDAOImpl instance = new BlogDAOImpl();
        PostCate expResult = null;
        PostCate result = instance.getBlogCategory(blogId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Paging method, of class BlogDAO.
     */
    @Test
    public void testPaging() throws Exception {
        System.out.println("Paging");
        int page = 0;
        ArrayList<Blog> list = null;
        BlogDAOImpl instance = new BlogDAOImpl();
        ArrayList<Blog> expResult = null;
        ArrayList<Blog> result = instance.Paging(page, list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
