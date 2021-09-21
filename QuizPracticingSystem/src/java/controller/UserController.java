/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UserController extends HttpServlet {

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

//            String userMail = request.getParameter("userMail");
//            String mess = "";
//            String password = request.getParameter("password");
//            User log = null;
//            UserDAO t = new UserDAO();
//            log = t.getUserLogin(userMail, password);
//
//            if (log == null) {
//                mess = "Sorry, username and/or password are/is invalid!";
//                sendDispatcher(request, response, "/login.html");
//
//            } else {
//                request.getSession().setAttribute("currUser", log);
//                request.getSession().setAttribute("role", log.getRoleId());
//                sendDispatcher(request, response, "/Home.jsp");
//
//            }
//            out.print(mess);
//            request.getRequestDispatcher("/index.html").include(request, response);
            //register
            if (service.equalsIgnoreCase("register")) {
                String mess = "";
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String confirmPass = request.getParameter("confirmPass");
                String userMail = request.getParameter("userMail");
                String userMobile = request.getParameter("userMobile");
                String txtGender = request.getParameter("gender");
                User addUser = new User();
                UserDAO ud = new UserDAO();

                boolean gender;

                if (!password.equals(confirmPass)) {
                    mess = "The confirm-password is not match with the password!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }

                String mailRegex = "";

                if (ud.isExistedEmail(userMail)) {
                    mess = "This email have already been used!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                
                String moblieRegex = "(09|03|07|08|05)+([0-9]{8})";
                if (!userMobile.matches(moblieRegex) || userMobile.length() != 10) {
                    mess = "The phone number is invalid";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }

                if (txtGender.equalsIgnoreCase("Male")) {
                    gender = true;
                } else {
                    gender = false;
                }

                addUser.setUserName(userName);
                addUser.setPassword(password);
                addUser.setUserMobile(userMobile);
                addUser.setUserMail(userMail);
                addUser.setGender(gender);
                ud.addUser(addUser);
            }
        }
    }

    public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserController.class
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
