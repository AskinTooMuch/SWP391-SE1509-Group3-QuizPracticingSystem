/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.SystemEmail;
import bean.User;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ResetPasswordController extends HttpServlet {

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
            UserDAO userDAO = new UserDAOImpl();
            //get email from page and send a resetPass mail to the address
            if (service.equalsIgnoreCase("resetPassword")) {
                String userMail = request.getParameter("enteredUserMail").trim();
                String mess = "";
                //check email if it is true
                if (userMail.length() == 0 || userMail == null) {
                    mess = "You have to input your email";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                } else if (userDAO.getUserByMail(userMail) == null) {
                    mess = "Email not existed!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                } else {
                    SystemEmail se = new SystemEmail();
                    long milis = System.currentTimeMillis();
                    String resetPassLink = "http://localhost:8080/QuizPracticingSystem/login/resetPass.jsp?userMail="
                            + userMail + "&createTime=" + milis;
                    se.sendEmail(userMail, "Reset password link", resetPassLink);
                    mess = "An reset password link have been sent to your email address";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                }
            }

            //get new pass and save to the database
            if (service.equalsIgnoreCase("resetPage")) {
                String mess;
                String userMail = request.getParameter("userMail");
                String newPass = request.getParameter("newPass");
                String confirmNewPass = request.getParameter("confirmNewPass");
                User user = userDAO.getUserByMail(userMail);
                if (newPass.equals(confirmNewPass)) { // if cofirm password match the password then change pass
                    user.setPassword(newPass);
                    userDAO.updateUser(user);
                    mess = "Your password have been reset";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                } else { // if cofirm password dont match the password then print out alert mess
                    mess = "Confirm password dont match";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
