/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  21/9/21     1.0         ChucNVHE150618  First Deploy
 *  23/9/21     1.0         TungBTHE150621  Add login service
 *  23/9/21     1.0         DuongNHHE150328 Add register
 *  24/9/21     1.0         ChucNVHE150618  Add changePassword service
 *  24/9/21     1.0         DuongNHHE150328 Add reset password function 
 *  27/9/21     1.0         DuongNHHE150328 Add edit profile function, upload image  
 */
package controller;

import bean.*;
import dao.impl.UserDAOImpl;
import dao.impl.UserRoleDAOImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import dao.UserDAO;
import dao.UserRoleDAO;

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
            UserDAO userInterface = new UserDAOImpl();
            /*Log in*/
            if (service.equalsIgnoreCase("login")) {
                String userMail = request.getParameter("userMail");
                String mess = "";
                String password = request.getParameter("password");
                User log = null;
                UserDAO t = new UserDAOImpl();
                UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

                log = t.getUserLogin(userMail, password);
                //validate user log in, if wrong, re-login
                if (log == null) {
                    mess = "Sorry, username and/or password are/is invalid!";
                    request.setAttribute("mess", mess);
                    sendDispatcher(request, response, "login/login.jsp");
                    return;
                    //if loged in, get user role and forward to homepage with their role    
                } else {
                    request.getSession().setAttribute("currUser", log);
                    request.getSession().setAttribute("role", userRoleDAO.getUserRoleById(log.getRoleId()));
                }
                out.print(mess);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            /* Log out */
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

                //check blank input fields
                if (userName.length() == 0 || password.length() == 0
                        || confirmPass.length() == 0
                        || userMail.length() == 0 || userMobile.length() == 0
                        || txtGender.length() == 0) {
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
                if (!userMail.matches(mailRegex)) {
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
                mess = "An confirm mail have been sent to your email address!";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("login/register.jsp").forward(request, response);
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
                String userMail = request.getParameter("enteredUserMail").trim();
                String mess = "";
                //check email if it is true
                if (userMail.length() == 0 || userMail == null) {
                    mess = "You have to input your email";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                } else if (userInterface.getUserByMail(userMail) == null) {
                    mess = "Email not existed!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/resetPass.jsp").forward(request, response);
                    return;
                } else {
                    sendResetMail(userMail);
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
                User user = userInterface.getUserByMail(userMail);
                if (newPass.equals(confirmNewPass)) { // if cofirm password match the password then change pass
                    user.setPassword(newPass);
                    userInterface.updateUser(user);
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

            //update user information
            if (service.equalsIgnoreCase("editProfile")) {
                String mess = "";
                User currUser = (User) request.getSession().getAttribute("currUser");
                String userName = request.getParameter("userName").trim();
                String userMobile = request.getParameter("userMobile").trim();
                String txtGender = request.getParameter("gender").trim();
                boolean gender;

                //check if any input is blank
                if (userName.length() == 0 || userMobile.length() == 0
                        || txtGender.length() == 0) {
                    mess = "You have to input all information!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/editProfile.jsp")
                            .forward(request, response);
                    return;
                }

                //check if this Moblie already existed in the system
                if (userInterface.getUserByMobile(userMobile) != null
                        && !userMobile.equals(userInterface.getUserById(currUser.getUserId()).getUserMobile())) {
                    mess = "The phone number is already been used";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/editProfile.jsp")
                            .forward(request, response);
                    return;
                }

                //check if the moblie is in right fomat and length
                String moblieRegex = "(09|03|07|08|05)+([0-9]{8})";
                if (!userMobile.matches(moblieRegex) || userMobile.length() != 10) {
                    mess = "The phone number is invalid";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("login/editProfile.jsp")
                            .forward(request, response);
                    return;
                }

                //convert gender to booolean type
                if (txtGender.equalsIgnoreCase("Male")) {
                    gender = true;
                } else {
                    gender = false;
                }
                currUser.setUserName(userName);
                currUser.setUserMobile(userMobile);
                currUser.setGender(gender);
                userInterface.updateUser(currUser);
                request.setAttribute("currUser",
                        userInterface.getUserById(currUser.getUserId()));
                request.getRequestDispatcher("index.jsp").
                        forward(request, response);
            }

            //upload or change profile image
            if (service.equalsIgnoreCase("uploadImage")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                String filename = null;
                try {
                    // Create a factory for disk-based file items
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                    factory.setRepository(repository);
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List<FileItem> items = upload.parseRequest(request);
                    Iterator<FileItem> iter = items.iterator();
                    HashMap<String, String> fields = new HashMap<>();
                    while (iter.hasNext()) {
                        FileItem item = iter.next();
                        if (item.isFormField()) {
                            fields.put(item.getFieldName(), item.getString());
                            String name = item.getFieldName();
                            String value = item.getString();
                            System.out.println(name + " " + value);
                        } else {
                            filename = item.getName();
                            if (filename == null || filename.equals("")) {
                                break;
                            } else {
                                Path path = Paths.get(filename);
                                String storePath = servletContext.getRealPath("/upload");
                                File uploadFile = new File(storePath + "/" + path.getFileName());
                                if (uploadFile.canRead()) {
                                    uploadFile.delete();
                                }
                                item.write(uploadFile);
                            }
                            out.println(filename);
                        }
                    }
                } catch (FileUploadException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                currUser.setProfilePic(filename);
                userInterface.updateUser(currUser);
                request.getSession().setAttribute("currUser", userInterface.getUserById(currUser.getUserId()));
                response.sendRedirect("login/userProfile.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
