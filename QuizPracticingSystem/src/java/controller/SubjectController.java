/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SubjectController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  27/9/21     1.0         ChucNVHE150618  First Deploy
*/
package controller;

import bean.Subject;
import bean.User;
import bean.UserRole;
import dao.impl.SubjectDAOImpl;
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
import dao.SubjectDAO;

public class SubjectController extends HttpServlet {

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
            /* Get service and initialise the subjectDAO */
            String service = request.getParameter("service");
            SubjectDAO subjectDAO = new SubjectDAOImpl();
            
            /**
             * Service course content list: for admin and expert to check the 
             * proper subject, depends on the role
             */
            if (service.equalsIgnoreCase("courseContentList")) {
                /* Get user and role on session scope */
                User currUser = (User)request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole)request.getSession().getAttribute("role");
                /* If user is not logged in, redirect to index */
                if ((currUser == null) || (currRole == null)){
                    sendDispatcher(request, response, "index.jsp");
                } else if (currRole.getUserRoleName().equalsIgnoreCase("expert")) {    /* Role is expert: get the assigned subjects */
                    /* Get assigned list */
                    ArrayList<Subject> featuredSubjectList = subjectDAO.getSubjectsAssigned(currUser.getUserId());
                    /* Set attribute and send it to course Content page */
                    request.setAttribute("courseContentSubjectList", featuredSubjectList);
                    sendDispatcher(request, response, "jsp/courseContentList.jsp");
                }   else if (currRole.getUserRoleName().equalsIgnoreCase("admin")) {    /* Role is admin: load all subject */
                    /* Get all subject */
                    ArrayList<Subject> allSubject = subjectDAO.getAllSubjects();
                    /* Set attribute and send it to course content page */
                    request.setAttribute("courseContentSubjectList", allSubject);
                    sendDispatcher(request, response, "jsp/courseContentList.jsp");
                }   else { /* If the user is logged in but not admin or expert, send back to index.jsp */
                    sendDispatcher(request, response, "index.jsp");
                }
                
            }
            
            /**
             * Service course content detail: for admin and expert to check the 
             * subject detail and edit it
             */
            if (service.equalsIgnoreCase("courseContentDetail")) {
                /* Get user and role on session scope */
                User currUser = (User)request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole)request.getSession().getAttribute("role");
                /* If user is not logged in, or not admin/expert, redirect to index */
                if ((currUser == null) || (currRole == null) || 
                        ((!currRole.getUserRoleName().equalsIgnoreCase("admin")) &&
                        (!currRole.getUserRoleName().equalsIgnoreCase("expert")))){
                    sendDispatcher(request, response, "error.jsp");
                } 
                /* Else: get the subject with the set id and redirect to courseContentDetail page*/
                else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                    Subject courseContent  = subjectDAO.getSubjectbyId(subjectId);
                    request.setAttribute("subject", courseContent);
                    sendDispatcher(request, response, "jsp/courseContentDetail.jsp");
                }
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    /* Forward the request to the destination, catch any unexpected exceptions and log it */
    public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubjectController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
