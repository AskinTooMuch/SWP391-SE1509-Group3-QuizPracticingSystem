/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 26, 2021, 9:12:17 AM
 *  UserController map
 *  Quiz practicing system

 *  Record of change:
 *  Date        Version     Author          Description
 *  26/10/21     1.0         TuanPAHE150543  First Deploy
 */
package controller;

import bean.Registration;
import bean.RegistrationManage;
import bean.Subject;
import bean.User;
import bean.UserRole;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tuan
 */
public class RegistrationController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            RegistrationDAO registrationInterface = new RegistrationDAOImpl();
            /**
             * Service: get all Subject, User
             */
            if (service.equalsIgnoreCase("getFilterInformation")) {
                String message = (String) request.getAttribute("message");
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                UserDAO userDAO = new UserDAOImpl();
                ArrayList<Subject> listSubject = subjectDAO.getAllSubjects();
                request.setAttribute("listFilterSubject", listSubject);
                ArrayList<User> listUser = userDAO.getUserAllUser();
                request.setAttribute("listFilterUser", listUser);
                request.getRequestDispatcher("jsp/registrationList.jsp").forward(request, response);
                if (message != null) {
                    request.setAttribute("message", message);
                }
            }

            /**
             * Service: filter Registration by subjectId, userId
             */
            if (service.equalsIgnoreCase("filterRegistration")) {
                /* Get user and role on session scope */
                User currUser = (User) request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole) request.getSession().getAttribute("role");
                /* If user is not logged in, or not admin/expert, redirect to index */
                if ((currUser == null) || (currRole == null)
                        || ((!currRole.getUserRoleName().equalsIgnoreCase("admin"))
                        && (!currRole.getUserRoleName().equalsIgnoreCase("sale")))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    ArrayList<RegistrationManage> listRegistrationManage = registrationInterface.getFilterRegistration(subjectId, userId);
                    request.setAttribute("listRegistrationManage", listRegistrationManage);
                    request.getRequestDispatcher("jsp/registrationList.jsp").forward(request, response);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
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
