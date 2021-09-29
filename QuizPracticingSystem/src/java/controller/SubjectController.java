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
import dao.SubjectINT;
import dao.impl.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
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
            String service = request.getParameter("service");
            SubjectINT subjectInterface = new SubjectDAO();
            
            if (service.equalsIgnoreCase("courseContentList")) {
                User currUser = (User)request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole)request.getSession().getAttribute("role");
                /* If user is not logged in, redirect to index */
                if ((currUser == null) || (currRole == null)){
                    sendDispatcher(request, response, "index.jsp");
                } else if (currRole.getUserRoleName().equalsIgnoreCase("expert")) {    /* Role is expert: get the assigned subjects */
                    ArrayList<Subject> featuredSubjectList = subjectInterface.getSubjectsAssigned(currUser.getUserId());
                    request.setAttribute("subjectList", featuredSubjectList);
                    sendDispatcher(request, response, "jsp/subjectList.jsp");
                }   else if (currRole.getUserRoleName().equalsIgnoreCase("admin")) {    /* Role is admin: load all subject */
                    ArrayList<Subject> allSubject = subjectInterface.getAllSubjects();
                    request.setAttribute("subjectList", allSubject);
                    sendDispatcher(request, response, "jsp/subjectList.jsp");
                }   else {
                    sendDispatcher(request, response, "index.jsp");
                }
                
            }
        }
    }
    
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
