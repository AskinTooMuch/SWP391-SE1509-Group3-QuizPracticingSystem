/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         ChucNVHE150618  First Deploy
 *  24/9/21     1.0         ChucNVHE150618  Add changePassword service
*/
package controller;

import bean.*;
import dao.UserINT;
import dao.UserRoleINT;
import dao.impl.UserDAO;
import dao.impl.UserRoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            UserINT userInterface = new UserDAO();
            if (service.equalsIgnoreCase("login")) {
                String userMail = request.getParameter("userMail");
                String mess = "";
                String password = request.getParameter("password");
                User log = null;
                UserINT t = new UserDAO();
                UserRoleINT userRoleDAO = new UserRoleDAO();
                
                log = t.getUserLogin(userMail, password);

                if (log == null) {
                    mess = "Sorry, username and/or password are/is invalid!";
                    sendDispatcher(request, response, "login/login.jsp");
                    return;

                } else {
                    request.getSession().setAttribute("currUser", log);
                    request.getSession().setAttribute("role", userRoleDAO.getUserRoleById(log.getRoleId()));
                }
                out.print(mess);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            if (service.equalsIgnoreCase("logout")) {
                request.getSession().invalidate();
                sendDispatcher(request, response, "index.jsp");
            }
            
            //get all atribute from page then check validate and save to database
            if (service.equalsIgnoreCase("register")) {
                String mess = "";
                String userName = request.getParameter("userName").trim();
                String password = request.getParameter("password").trim();
                String confirmPass = request.getParameter("confirmPass").trim();
                String userMail = request.getParameter("userMail").trim();
                String userMobile = request.getParameter("userMobile").trim();
                String txtGender = request.getParameter("gender").trim();
                boolean gender;
                User addUser = new User();

                if (userName == null || password == null || confirmPass == null 
                        || userMail == null || userMobile == null 
                        || txtGender == null) {
                    mess = "You have to input all information!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/register.jsp").forward(request, response);
                    return;
                }

                //check if comfirm pass is the same as pass
                if (!password.equals(confirmPass)) {
                    mess = "The confirm-password is not match with the password!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/register.jsp").forward(request, response);
                    return;
                }

                //check validate mail
                String mailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                if ( !userMail.matches(mailRegex)) {
                    mess = "The Email is invalid !";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/register.jsp").forward(request, response);
                    return;
                }

                //check if this email already existed in the system
                if (userInterface.getUserByMail(userMail) != null) {
                    mess = "This email have already been used!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/register.jsp").forward(request, response);
                    return;
                }

                //check if this Moblie already existed in the system
                if (userInterface.getUserByMobile(userMobile) != null) {
                    mess = "The phone number is already been used";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/register.jsp").forward(request, response);
                    return;
                }

                //check if the moblie is in right fomat and length
                String moblieRegex = "(09|03|07|08|05)+([0-9]{8})";
                if (!userMobile.matches(moblieRegex) || userMobile.length() != 10) {
                    mess = "The phone number is invalid";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/register.jsp").forward(request, response);
                    return;
                }

                //convert gender to booolean type
                if (txtGender.equalsIgnoreCase("Male")) {
                    gender = true;
                } else {
                    gender = false;
                }

                //setup information and add to the database
                addUser.setUserName(userName);
                addUser.setPassword(password);
                addUser.setUserMobile(userMobile);
                addUser.setUserMail(userMail);
                addUser.setGender(gender);
                userInterface.addUser(addUser);

                SystemEmail se = new SystemEmail();
                String confirmLink = "http://localhost:8080/QuizPracticingSystem"
                        + "/userController?service=confirmAccount&userMail=" 
                        + userMail;
                se.sendEmail(userMail, "Confirm Your Account", confirmLink);
                out.println("<p>An confirm mail have been sent to your email address!</p>");
            }

            //change status for user account
            if (service.equalsIgnoreCase("confirmAccount")) {
                String userMail = request.getParameter("userMail");
                User user = userInterface.getUserByMail(userMail);
                userInterface.changeStatus(user.getUserId(), true);
                out.println("Confirmed");
                out.println("<a href=" + "login/login.jsp" 
                        + ">Login</a>");
            }

            //get email from page and send a resetPass mail to the address
            if (service.equalsIgnoreCase("resetPassword")) {
                String userMail = request.getParameter("userMail").trim();
                
                //check email if it is true
                if (userMail.length() == 0  || userMail == null) {
                    out.println("You have to input your email");
                    request.getRequestDispatcher("login/resetPass.jsp").include(request, response);
                    return;
                } else if (userInterface.getUserByMail(userMail) == null) {
                    out.println("Email not existed!");
                    request.getRequestDispatcher("login/resetPass.jsp").include(request, response);
                    return;
                } else {
                    sendResetMail(userMail);
                    out.println("An reset password link have been sent to your email address");
                    request.getRequestDispatcher("login/resetPass.jsp").include(request, response);
                    return;
                }
            }

            
            //get new pass and svae to the database
            if (service.equalsIgnoreCase("resetPage")) {
                String userMail = request.getParameter("userMail");
                String newPass = request.getParameter("newPass");
                String confirmNewPass = request.getParameter("confirmNewPass");
                User user = userInterface.getUserByMail(userMail);
                if (newPass.equals(confirmNewPass)) {
                    user.setPassword(newPass);
                    userInterface.updateUser(user);
                    out.println("Your password have been reset");
                    out.println("<a href=" + "jsp/login.jsp" + ">Login</a>");
                    return;
                } else {
                    out.println("The confirm-password is not match with the password!");
                    return;
                }

            }

            if (service.equalsIgnoreCase("changePassword")) {
                String password = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                User currUser = (User) request.getSession().getAttribute("currUser");

                if (currUser.getPassword().equals(password)) {
                    currUser.setPassword(newPassword);
                    int i = userInterface.updateUser(currUser);
                    if (i != 0) {
                        request.setAttribute("message", "Password changed successfully.");
                        request.setAttribute("color", "green");
                        sendDispatcher(request, response, "login/changePassword.jsp");
                    } else {
                        request.setAttribute("message", "Password changed failed.");
                        request.setAttribute("color", "red");
                        sendDispatcher(request, response, "login/changePassword.jsp");
                    }
                } else {
                    request.setAttribute("message", "Password incorrect.");
                    request.setAttribute("color", "red");
                    sendDispatcher(request, response, "login/changePassword.jsp");
                }

            }
        }
    }
    
    //create reset password link and send to the email address
    public void sendResetMail(String userMail) {
        SystemEmail se = new SystemEmail();
        long milis = System.currentTimeMillis();
        String resetPassLink = "http://localhost:8080/QuizPracticingSystem/login/resetPass.jsp?userMail="
                + userMail + "&createTime=" + milis;
        se.sendEmail(userMail, "Reset password link", resetPassLink);
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
