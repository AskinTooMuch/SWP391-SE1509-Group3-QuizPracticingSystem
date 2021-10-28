/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 27, 2021
 *  User List servlet
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  27/10/21    1.0         ChucNVHE150618  First Deploy
 */
package controller.chucnv;

import bean.Subject;
import bean.User;
import bean.UserRole;
import dao.UserDAO;
import dao.UserRoleDAO;
import dao.impl.UserDAOImpl;
import dao.impl.UserRoleDAOImpl;
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
 *
 * @author admin
 */
public class UserListController extends HttpServlet {

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
            /*Service is null, redirect user to index*/
            if ((service == null) || (service.trim().equals(""))) {
                service = "loadCriteria";
            }
            
            UserDAO userDAO = new UserDAOImpl();
            UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
            /* Get user and role on session scope */
            User currUser = (User) request.getSession().getAttribute("currUser");
            UserRole currRole = (UserRole) request.getSession().getAttribute("role");
            /* If user is not logged in, redirect to index */
            if ((currUser == null) || (currRole == null)) {
                sendDispatcher(request, response, "index.jsp");
            } else if (currRole.getUserRoleName().equalsIgnoreCase("admin")) {
                /*Get page number*/
                int page;
                if (request.getParameter("page") == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                request.setAttribute("page", page);
                /**
                 * Service loadCriteria : load default lists and searched lists
                 */
                if (service.equalsIgnoreCase("loadCriteria")){
                    /* Role is admin: load all user */
                    String criteriaType = request.getParameter("criteriaType");
                    if (criteriaType == null){
                        criteriaType = "";
                    }
                    String criteria = request.getParameter("criteria");
                    if (criteria == null){
                        criteria = "";
                    }
                    /* Get user with criteria, paginated */
                    ArrayList<User> userPage = userDAO.getTrueAllUserPaging(page,criteriaType,criteria);
                    for (User user : userPage) {
                        user.setUserRole(userRoleDAO.getUserRoleById(user.getRoleId()));
                    }
                    int maxPage = (int) Math.ceil((double) userDAO.getTrueAllUserPaging(-1,criteriaType,criteria).size() / 7);
                    request.setAttribute("maxPage", maxPage);
                    /*Reset filter*/
                    request.setAttribute("genderFilter", -1);
                    request.setAttribute("roleFilter", -1);
                    request.setAttribute("statusFilter", -1);
                    
                    request.setAttribute("userRoleList", userRoleDAO.getAllStatusUserRole());
                    /* Set attribute and send it to user list page */
                    request.setAttribute("userPageList", userPage);
                    sendDispatcher(request, response, "jsp/userList.jsp");
                }
                
                /**
                 * Service Filter: load userList filtered
                 */
                if (service.equalsIgnoreCase("filter")){
                    int gender = Integer.parseInt(request.getParameter("genderFilter"));
                    int role = Integer.parseInt(request.getParameter("roleFilter"));
                    int status = Integer.parseInt(request.getParameter("statusFilter"));
                    request.setAttribute("genderFilter", gender);
                    request.setAttribute("roleFilter", role);
                    request.setAttribute("statusFilter", status);
                    /*Get user list*/
                    ArrayList<User> userPage = userDAO.getFilteredUserPaging(page, gender, role, status);
                    for (User user : userPage) {
                        user.setUserRole(userRoleDAO.getUserRoleById(user.getRoleId()));
                    }
                    
                    int maxPage = (int) Math.ceil((double) userDAO.getFilteredUserPaging(-1, gender, role, status).size() / 7);
                    request.setAttribute("maxPage", maxPage);
                    request.setAttribute("userRoleList", userRoleDAO.getAllStatusUserRole());
                    /* Set attribute and send it to user list page */
                    request.setAttribute("userPageList", userPage);
                    sendDispatcher(request, response, "jsp/userList.jsp");
                }
                
                
            } else {
                /* If the user is logged in but not admin, send back to index.jsp */
                sendDispatcher(request, response, "index.jsp");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserListController.class
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
