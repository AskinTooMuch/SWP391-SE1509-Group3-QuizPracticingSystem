/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  HomeController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         ChucNVHE150618  First Deploy
 *  24/9/21     1.0         ChucNVHE150618  Add homepage service
 *  26/9/21     1/0         ChucNVHE150618  Add Subject list service
*/
package controller;

import bean.Blog;
import bean.Slider;
import bean.Subject;
import dao.impl.BlogDAOImpl;
import dao.impl.SliderDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BlogDAO;
import dao.SliderDAO;
import dao.SubjectDAO;
import dao.UserDAO;

/**
 *
 * @author tuan
 */
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* If the service is null(normally on first load, set the service is homePage) */
            String service = request.getParameter("service");
            if (service==null) service="homePage";
            /* Initialize the DAO needed */
            UserDAO userInterface = new UserDAOImpl();
            BlogDAO blogInterface = new BlogDAOImpl();
            SubjectDAO subjectInterface = new SubjectDAOImpl();
            SliderDAO sliderInterface = new SliderDAOImpl();
            /*Service: Homepage. If the page is loaded without some attribute(First time) it will gets redirected here.*/
            if (service.equalsIgnoreCase("homePage")) {
                /* Get the needed lists for index and set attribute to request */
                ArrayList<Subject> featuredSubjectList = subjectInterface.getFeaturedSubjects();
                request.setAttribute("subjectList", featuredSubjectList);
                ArrayList<Slider> sliderList = sliderInterface.getSlider();
                request.setAttribute("sliderList", sliderList);
                ArrayList<Blog> blogList = blogInterface.getAllBlog();
                request.setAttribute("blogList", blogList);
                /* Redicrect to index.jsp */
                sendDispatcher(request, response, "index.jsp");
            }
            /*Service: subjectList. Load subjectList and redirect user.*/
//            if (service.equalsIgnoreCase("subjectList")) {
//                
//                
//                /* Get subject list and set attribute */
//                ArrayList<Subject> subjectList = subjectInterface.getSubjectsPaging(1);
//                request.setAttribute("subjectList", subjectList);
//                /* Redirect to subjectList.jsp */
//                sendDispatcher(request, response, "jsp/subjectList.jsp");
//            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
    /* Forward the request to the destination, catch any unexpected exceptions and log it */
    public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(HomeController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
