/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 18, 2021
 *  Course Content List servlet
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  19/10/21    1.0         ChucNVHE150618  First Deploy
 */
package controller.chucnv;

import bean.Subject;
import bean.User;
import bean.UserRole;
import dao.SubjectDAO;
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

/**
 *  This class has the process request of service course content list
 * @author ChucNV
 */
public class CourseContentList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Service course content list: for admin and expert to check the
     * proper subject, depends on the role
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            SubjectDAO subjectDAO = new SubjectDAOImpl();
            /* Get user and role on session scope */
            User currUser = (User) request.getSession().getAttribute("currUser");
            UserRole currRole = (UserRole) request.getSession().getAttribute("role");
            /* If user is not logged in, redirect to index */
            if ((currUser == null) || (currRole == null)) {
                sendDispatcher(request, response, "index.jsp");
            } else if (currRole.getUserRoleName().equalsIgnoreCase("expert")) {
                /* Role is expert: get the assigned subjects */
                int page;
                if (request.getAttribute("pageNumber") == null) {
                    page = 1;
                } else {
                    page = (int) request.getAttribute("pageNumber");
                }
                /* Get assigned list */
                ArrayList<Subject> featuredSubjectList = subjectDAO.getSubjectsAssignedPaging(currUser.getUserId(),page);
                int maxPage = (int) Math.ceil((double) subjectDAO.getSubjectsAssigned(currUser.getUserId()).size() / 7);
                request.setAttribute("page", page);
                request.setAttribute("maxPage", maxPage);
                /* Set attribute and send it to course Content page */
                request.setAttribute("courseContentSubjectList", featuredSubjectList);
                sendDispatcher(request, response, "jsp/courseContentList.jsp");
            } else if (currRole.getUserRoleName().equalsIgnoreCase("admin")) {
                /* Role is admin: load all subject */
                int page;
                if (request.getAttribute("pageNumber") == null) {
                    page = 1;
                } else {
                    page = (int) request.getAttribute("pageNumber");
                }
                /* Get all subject */
                int maxPage = (int) Math.ceil((double) subjectDAO.getTrueAllSubjects().size() / 7);
                request.setAttribute("page", page);
                request.setAttribute("maxPage", maxPage);
                ArrayList<Subject> allSubject = subjectDAO.getTrueSubjectsPaging(page);
                /* Set attribute and send it to course content page */
                request.setAttribute("courseContentSubjectList", allSubject);
                sendDispatcher(request, response, "jsp/courseContentList.jsp");
            } else {
                /* If the user is logged in but not admin or expert, send back to index.jsp */
                sendDispatcher(request, response, "index.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Forward the request to the destination, catch any unexpected exceptions and log it
     * @param request   Request of the servlet
     * @param response  Response of the servlet
     * @param path      Forward address
     */
    public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubjectList.class
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
