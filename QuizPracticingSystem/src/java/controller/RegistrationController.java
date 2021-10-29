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

import bean.PricePackage;
import bean.Registration;
import bean.RegistrationManage;
import bean.Subject;
import bean.User;
import bean.UserRole;
import dao.PricePackageDAO;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import dao.impl.PricePackageDAOImpl;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
             * Service: get all Subject, User
             */
            if (service.equalsIgnoreCase("getInformationDetail")) {
                String message = (String) request.getAttribute("message");
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                UserDAO userDAO = new UserDAOImpl();
                PricePackageDAO pricePackageDAO = new PricePackageDAOImpl();
                ArrayList<User> listUser = userDAO.getUserAllUser();
                request.setAttribute("listUser", listUser);
                ArrayList<PricePackage> listPackage = pricePackageDAO.getAllPricePackage();
                request.setAttribute("listPackage", listPackage);
                request.getRequestDispatcher("jsp/registrationDetail.jsp").forward(request, response);
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
                    SubjectDAO subjectDAO = new SubjectDAOImpl();
                    UserDAO userDAO = new UserDAOImpl();
                    PricePackageDAO pricePackageDAO = new PricePackageDAOImpl();
                    int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    ArrayList<Subject> listSubject = subjectDAO.getAllSubjects();
                    request.setAttribute("listFilterSubject", listSubject);
                    ArrayList<User> listUser = userDAO.getUserAllUser();
                    request.setAttribute("listFilterUser", listUser);
                    ArrayList<RegistrationManage> listRegistrationManage = registrationInterface.getFilterRegistration(subjectId, userId);
                    request.setAttribute("listRegistrationManage", listRegistrationManage);
                    request.getRequestDispatcher("jsp/registrationList.jsp").forward(request, response);
                }
            }

            /**
             * Service: get edit registration information
             */
            if (service.equalsIgnoreCase("editRegistration")) {
                /* Get user and role on session scope */
                User currUser = (User) request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole) request.getSession().getAttribute("role");
                /* If user is not logged in, or not admin/expert, redirect to index */
                if ((currUser == null) || (currRole == null)
                        || ((!currRole.getUserRoleName().equalsIgnoreCase("admin"))
                        && (!currRole.getUserRoleName().equalsIgnoreCase("sale")))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    int registrationId = Integer.parseInt(request.getParameter("registrationId"));
                    String type = request.getParameter("type");
                    RegistrationDAO registrationDAO = new RegistrationDAOImpl();
                    if (type.equalsIgnoreCase("update")) {
                        SubjectDAO subjectDAO = new SubjectDAOImpl();
                        UserDAO userDAO = new UserDAOImpl();
                        Registration updateRegistration = registrationInterface.getRegistrationById(registrationId);
                        ArrayList<Subject> subjectList = subjectDAO.getAllSubjects();
                        request.getRequestDispatcher("jsp/updateQuestion.jsp").forward(request, response);
                    }
                }
            }

            /**
             * Service course content detail: add new subject lessons
             */
            if (service.equalsIgnoreCase("addRegistration")) {
                /* Get user and role on session scope */
                User currUser = (User) request.getSession().getAttribute("currUser");
                UserRole currRole = (UserRole) request.getSession().getAttribute("role");
                /* If user is not logged in, or not admin/expert, redirect to index */
                if ((currUser == null) || (currRole == null)
                        || ((!currRole.getUserRoleName().equalsIgnoreCase("admin"))
                        && (!currRole.getUserRoleName().equalsIgnoreCase("sale")))) {
                    sendDispatcher(request, response, "error.jsp");
                } else {
                    UserDAO userDAO = new UserDAOImpl();
                    PricePackageDAO pricePackageDAO = new PricePackageDAOImpl();
                    String message = "";
                    String color = "red";
                    /* Get parameters from jsp */
                    int userId = Integer.parseInt(request.getParameter("userId").trim());          
                    int packageId = Integer.parseInt(request.getParameter("packageId").trim());
                    double cost = Double.parseDouble(request.getParameter("cost"));
                    String dateFrom = request.getParameter("validFrom"); // get request date user selected
                    Date validFrom = Date.valueOf(dateFrom); // get values of  date  
                    Date validTo = Date.valueOf(dateFrom);
                    PricePackage pricePackage = pricePackageDAO.getPricePackageById(packageId);
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    calendar.setTime(validTo);
                    calendar.add(java.util.Calendar.MONTH, pricePackage.getDuration());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");                  
                    validTo = Date.valueOf(dateFormat.format(calendar.getTime()));
                    int lastUpdateBy = currUser.getUserId();
                    String note = request.getParameter("note").trim();
                    boolean status = request.getParameter("status").equals("1");
                    /* Check boundaries */
                    if (userId == 0) {
                        message = "User can not be empty";
                    } else {
                        /* Add new subject lesson */
                        Registration updateRegistration = new Registration(0, userId, null, packageId, cost, validFrom, validTo, lastUpdateBy, note, status);
                        int check = registrationInterface.addRegistration(updateRegistration);
                        if (check > 0) {
                            color = "green";
                            message = "Add registration successfully.";
                        } else {
                            message = "Add registration failed.";
                        }
                    }

                    /* Get the needed lists and redirect to the courseContentJsp */
                    ArrayList<User> listUser = userDAO.getUserAllUser();
                    request.setAttribute("listUser", listUser);
                    ArrayList<PricePackage> listPackage = pricePackageDAO.getAllPricePackage();
                    request.setAttribute("listPackage", listPackage);
                    request.setAttribute("message", message);
                    sendDispatcher(request, response, "jsp/registrationList.jsp");
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
