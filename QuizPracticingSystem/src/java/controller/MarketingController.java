/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 18, 2021
 *  MarketingController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  18/9/21     1.0         NamDHHe150519   First Deploy
    19/9/21     1.0         NamDHHe150519   update service
    9/10/21     1.0         NamDHHe150519   update service dashboard
 */
package controller;

import bean.*;
import dao.impl.BlogDAOImpl;
import dao.impl.PostCateDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BlogDAO;
import dao.PostCateDAO;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    static final int CARD_PER_PAGE = 9;
    static final int DEFAULT_PAGE = 1;
    static final long MILISECOND_PER_WEEK = 604800000;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            BlogDAO blogInterface = new BlogDAOImpl();
            PostCateDAO postCateInterface = new PostCateDAOImpl();

            /**
             * Service blog list: browse all blog in database
             */
            if (service.equalsIgnoreCase("blogList")) {
                ArrayList<Blog> blogList = blogInterface.getAllTrueBlog();
                //neu tim kiem theo category hoac string
                String[] searchCate = request.getParameterValues("category");
                String searchString = request.getParameter("search");

                if ((searchCate != null) || (searchString != null)) {
                    blogList = blogInterface.getBlogByCategoryAndTitle(searchCate, searchString);        //searched blogList 
                    //phan trang sau khi tim kiem theo category
                    String pagingUrl = "";                                                               //url connect to ...?page=          
                    if (searchCate != null) {
                        for (String category : searchCate) {
                            pagingUrl += "&category=" + category;                                        //Add category parameter
                        }
                    }
                    if (searchString != null) {
                        pagingUrl += "&search=" + searchString;                                          //Add search parameter
                    }
                    request.setAttribute("pagingUrl", pagingUrl);
                }

                //xu li phan trang
                int listSize = blogList.size();
                int pageNumber = (listSize % CARD_PER_PAGE == 0) ? (listSize / CARD_PER_PAGE) : (listSize / CARD_PER_PAGE + 1); //Number of pages needed 
                String pageRaw = request.getParameter("page");
                int page;
                if (pageRaw == null) {
                    page = DEFAULT_PAGE;
                } else {
                    page = Integer.parseInt(pageRaw);
                }
                request.setAttribute("pagenum", pageNumber);
                request.setAttribute("page", page);

                ArrayList<Blog> paginatedBlogList = blogInterface.Paging(page, blogList);
                request.setAttribute("blogList", paginatedBlogList);
                //Send blog category list
                ArrayList<PostCate> postCateList = postCateInterface.getAllPostCates();
                request.setAttribute("postCateList", postCateList);
                //Send last blogs
                ArrayList<Blog> lastBlogs = blogInterface.getLastBlogs();
                request.setAttribute("lastBlogs", lastBlogs);

                request.getRequestDispatcher("jsp/blogList.jsp").forward(request, response);
            }

            /**
             * Service blog detail: show detail of the blog
             */
            if (service.equalsIgnoreCase("blogDetail")) {
                int blogId = Integer.parseInt(request.getParameter("blogId"));
                Blog blog = blogInterface.getBlogById(blogId);
                request.setAttribute("blog", blog);
                int blogCate = postCateInterface.getBlogCateByBlogId(blogId);
                String blogCateName = postCateInterface.getPostCateById(blogCate)
                        .getPostCateName();
                request.setAttribute("blogCateName", blogCateName);
                ArrayList<PostCate> postCateList = postCateInterface.getAllPostCates();
                request.setAttribute("postCateList", postCateList);
                ArrayList<Blog> lastBlogs = blogInterface.getLastBlogs();
                request.setAttribute("lastBlogs", lastBlogs);
                request.getRequestDispatcher("jsp/blogDetail.jsp").forward(request, response);
            }

            /**
             * This is for the Admin, Sale or Marketing to see and lookup for
             * the relevant management statistics in the system, with the
             * relevant links to the related screens
             */
            if (service.equalsIgnoreCase("dashboard")) {
                RegistrationDAO IRegistration = new RegistrationDAOImpl();
                SubjectDAO ISubject = new SubjectDAOImpl();

                String option = request.getParameter("option");
                String target = request.getParameter("target");
                String attribute = request.getParameter("attribute");
                String from = request.getParameter("from");
                String to = request.getParameter("to");
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = formatter.format(date.getTime());
                if (to == null) {
                    to = currentDate;
                }
                if (from == null) {
                    from = formatter.format(date.getTime() - MILISECOND_PER_WEEK);
                }
                request.setAttribute("currentDate", currentDate);
                request.setAttribute("from", from);
                request.setAttribute("to", to);

                if (option == null) {
                    option = "subject";
                    target = "new";
                    attribute = "revenue";
                }

                request.setAttribute("option", option);
                request.setAttribute("target", target);

                if (option.equals("subject")) {
                    request.setAttribute("attribute", attribute);
                    ArrayList<Subject> subjectList = new ArrayList();
                    if (target.equals("new")) {
                        subjectList = ISubject.get5LastAddedSubject();
                    } else if (target.equals("all")) {
                        subjectList = ISubject.getAllSubjects();
                    }

                    ArrayList<ItemDashboard> stasistic = IRegistration.getSubjectStasistic(from, to, subjectList, attribute);
                    ArrayList<String> subjectStasistic = IRegistration.convertJson(stasistic);
                    request.setAttribute("subjectStasistic", subjectStasistic);
                    ArrayList<String> nameList = IRegistration.getNameList(stasistic);
                    request.setAttribute("nameList", nameList);
                }

                if (option.equals("registration")) {
                    ArrayList<Registration> registrationList = IRegistration.getAllRegistration();
                    request.setAttribute("registrationList", registrationList);
                }

                if (option.equals("revenue")) {
                    if (target.equals("total")) {
                        ArrayList<ItemDashboard> stasistic = IRegistration.getRevenueStasistic(from, to);
                        ArrayList<String> subjectStasistic = IRegistration.convertJson(stasistic);
                        request.setAttribute("subjectStasistic", subjectStasistic);
                        ArrayList<String> nameList = IRegistration.getNameList(stasistic);
                        request.setAttribute("nameList", nameList);
                    } else if (target.equals("bySubjectCate")) {
                        ArrayList<ItemDashboard> stasistic = IRegistration.getRevenueStasisticBySubjectCate(from, to);
                        ArrayList<String> subjectStasistic = IRegistration.convertJson(stasistic);
                        request.setAttribute("subjectStasistic", subjectStasistic);
                        ArrayList<String> nameList = IRegistration.getNameList(stasistic);
                        request.setAttribute("nameList", nameList);
                    }
                }

                if (option.equals("customer")) {
                    if (target.equals("newlyRegistered")) {
                        
                    } else if (target.equals("newlyBought")) {

                    }
                }

                if (option.equals("trendOfOrderCounts")) {
                    if (target.equals("success")) {

                    } else if (target.equals("all")) {

                    }
                }
                request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);

            }
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            response.sendRedirect("error.jsp");
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
