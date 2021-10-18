/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 9, 2021
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  9/10/21     1.0         NamDHHe150519   update service dashboard
 */
package controller;

import bean.ItemDashboard;
import bean.Registration;
import bean.Subject;
import bean.User;
import static controller.MarketingController.MILISECOND_PER_WEEK;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMN
 */
public class DashboardController extends HttpServlet {

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

            RegistrationDAO IRegistration = new RegistrationDAOImpl();
            SubjectDAO ISubject = new SubjectDAOImpl();
            String option = request.getParameter("option");
            String target = request.getParameter("target");
            String attribute = request.getParameter("attribute");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = formatter.format(date.getTime());
            if (to == null) {
                to = currentDate;
            }
            if (from == null) {
                from = formatter.format(date.getTime() - MILISECOND_PER_WEEK);
            }
            request.setAttribute("currentDate", currentDate);
            request.setAttribute("from", from);
            request.setAttribute("to", to);

            if (option == null) {
                option = "subject";
                target = "new";
                attribute = "revenue";
            }

            request.setAttribute("option", option);
            request.setAttribute("target", target);
            ArrayList<String> statistics = new ArrayList();
            ArrayList<String> nameList = new ArrayList();
            if (option.equals("subject")) {
                request.setAttribute("attribute", attribute);
                ArrayList<Subject> subjectList = new ArrayList();
                if (target.equals("new")) {
                    subjectList = ISubject.get5LastAddedSubject();
                } else if (target.equals("all")) {
                    subjectList = ISubject.getAllSubjects();
                }

                ArrayList<ItemDashboard> subjectStatistics = IRegistration.getSubjectStatistics(from, to, subjectList, attribute);
                statistics = IRegistration.convertJson(subjectStatistics);
                nameList = IRegistration.getNameList(subjectStatistics);
            }

            if (option.equals("registration")) {
                ArrayList<ItemDashboard> registrationStatistics = IRegistration.getRegistrationStatistics(from, to);
                statistics = IRegistration.convertJson(registrationStatistics);
                nameList = IRegistration.getNameList(registrationStatistics);
            }

            if (option.equals("revenue")) {
                ArrayList<ItemDashboard> revenueStatistics = new ArrayList();
                if (target.equals("total")) {
                    revenueStatistics = IRegistration.getRevenueStatistics(from, to);
                } else if (target.equals("bySubjectCate")) {
                    revenueStatistics = IRegistration.getRevenueStatisticsBySubjectCate(from, to);
                }
                statistics = IRegistration.convertJson(revenueStatistics);
                nameList = IRegistration.getNameList(revenueStatistics);
            }

            if (option.equals("customer")) {
                UserDAO IUser = new UserDAOImpl();
                if (target.equals("newlyRegistered")) {
                    ArrayList<User> userList = IUser.get10NewUser();
                    request.setAttribute("userList", userList);
                } else if (target.equals("newlyBought")) {
                    ArrayList<Registration> registrationList = IRegistration.get10NewRegistration();
                    request.setAttribute("registrationList", registrationList);
                }
            }
            if (option.equals("trendOfOrderCounts")) {
                if (target.equals("success")) {

                } else if (target.equals("all")) {

                }
            }
            request.setAttribute("statistics", statistics);
            request.setAttribute("nameList", nameList);
            request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            response.sendRedirect("error.jsp");
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
