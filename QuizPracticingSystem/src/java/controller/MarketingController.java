/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.*;
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
    BlogDAO blogDAO = new BlogDAO();
    PostCateDAO postCateDAO = new PostCateDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");

            if (service.equalsIgnoreCase("blogList")) {
                String[] cate = request.getParameterValues("category");
                String page_raw = request.getParameter("page");
                String search = request.getParameter("search");
                int page;
                if (page_raw == null) page = 1;
                else page = Integer.parseInt(page_raw);
                ArrayList<Blog> blogList;
                blogList = blogDAO.getAllBlog();
                if (search != null || cate != null) {
                    blogList = blogDAO.getBlogByCategoryAndTitle(cate, search);
                }
                int size = blogList.size();
                int pagenum = (size % 9 == 0) ? (size / 9) : (size / 9 + 1);
                String alterUrl = "";
                for (String c : cate) {
                    alterUrl += "&category=" + c;
                }
                alterUrl += "%search=" + search;
                ArrayList<Blog> paginatedBlogList = blogDAO.Paging(page, blogList);
                ArrayList<PostCate> postCateList = postCateDAO.getAllPostCates();
                request.setAttribute("postCateList", postCateList);
                request.setAttribute("blogList", paginatedBlogList);
                request.setAttribute("alterUrl", alterUrl);
                request.setAttribute("pagenum", pagenum);
                request.setAttribute("page", page);
                request.getRequestDispatcher("blogList.jsp").forward(request, response);
            }

            if (service.equalsIgnoreCase("blogDetail")) {
                int blogId = Integer.parseInt(request.getParameter("blogId"));
                Blog blog = blogDAO.getBlogById(blogId);
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("blogDetail.jsp").forward(request, response);
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
