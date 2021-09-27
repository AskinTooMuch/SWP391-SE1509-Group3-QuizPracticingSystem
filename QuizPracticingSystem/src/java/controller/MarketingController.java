/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.*;
import dao.BlogINT;
import dao.PostCateINT;
import dao.impl.BlogDAO;
import dao.impl.PostCateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class MarketingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    BlogINT blogINT = new BlogDAO();
    PostCateINT postCateINT = new PostCateDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");

            if (service.equalsIgnoreCase("blogList")) {

                ArrayList<Blog> blogList = blogINT.getAllTrueBlog();
                //neu tim kiem theo category hoac string
                String[] cate = request.getParameterValues("category");
                String searchString = request.getParameter("search");

                if ((cate != null) || (searchString != null)) {
                    blogList = blogINT.getBlogByCategoryAndTitle(cate, searchString);       //searched blogList 
                    //phan trang sau khi tim kiem theo category
                    String pagingUrl = "";                                                  //url conncet to ...?page=          
                    if (cate != null) {
                        for (String category : cate) {
                            pagingUrl += "&category=" + category;                           //Add category parameter
                        }
                    }
                    if (searchString != null) {
                        pagingUrl += "&search=" + searchString;                             //Add search parameter
                    }
                    request.setAttribute("pagingUrl", pagingUrl);
                }

                //xu li phan trang
                int listSize = blogList.size();                                             //Number of blogs
                int pageNumber = (listSize % 9 == 0) ? (listSize / 9) : (listSize / 9 + 1); //Number of pages needed 
                String pageRaw = request.getParameter("page");
                int page;
                if (pageRaw == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(pageRaw);
                }
                request.setAttribute("pagenum", pageNumber);
                request.setAttribute("page", page);
                                
                ArrayList<Blog> paginatedBlogList = blogINT.Paging(page, blogList);
                request.setAttribute("blogList", paginatedBlogList);
                //Send blog category list
                ArrayList<PostCate> postCateList = postCateINT.getAllPostCates();
                request.setAttribute("postCateList", postCateList);
                //Send last blogs
                ArrayList<Blog> lastBlogs = blogINT.getLastBlogs();
                request.setAttribute("lastBlogs", lastBlogs);
               
                request.getRequestDispatcher("jsp/blogList.jsp").forward(request, response);
            }

            if (service.equalsIgnoreCase("blogDetail")) {
                
                int blogId = Integer.parseInt(request.getParameter("blogId"));
                Blog blog = blogINT.getBlogById(blogId);
                request.setAttribute("blog", blog);
                
                ArrayList<PostCate> postCateList = postCateINT.getAllPostCates();
                request.setAttribute("postCateList", postCateList);
                ArrayList<Blog> lastBlogs = blogINT.getLastBlogs();
                request.setAttribute("lastBlogs", lastBlogs);
                request.getRequestDispatcher("jsp/blogDetail.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
