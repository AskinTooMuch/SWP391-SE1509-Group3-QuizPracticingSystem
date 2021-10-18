/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Quiz;
import bean.QuizQuizHandle;
import bean.Subject;
import bean.User;
import dao.QuizDAO;
import dao.RegistrationDAO;
import dao.impl.QuizDAOImpl;
import dao.impl.RegistrationDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMN
 */
public class SimulationExamController extends HttpServlet {

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
            HttpSession session = request.getSession();
            QuizQuizHandle doingQuiz = (QuizQuizHandle) session.getAttribute("doingQuiz");
            //check if user currently taking a quiz
            if (doingQuiz != null) {
                request.setAttribute("doingQuiz", doingQuiz);
            }

            //information of simulation exam
            RegistrationDAO IRegistration = new RegistrationDAOImpl();
            User currUser = (User) session.getAttribute("currUser");
            String subjectSearchIdRaw = request.getParameter("subjectSearchId");
            int subjectSearchId = 0;
            if (subjectSearchIdRaw != null && !subjectSearchIdRaw.equalsIgnoreCase("")) {
                subjectSearchId = Integer.parseInt(subjectSearchIdRaw);
                request.setAttribute("subjectSearchId", +subjectSearchId);
            }

            String searchQuizName = request.getParameter("searchQuizName");
            if (searchQuizName != null && searchQuizName.length() > 100) {
                request.setAttribute("errorMess", "invalid length");
            }
            request.setAttribute("searchQuizName", searchQuizName);
            
            QuizDAO quizInterface = new QuizDAOImpl();
            ArrayList<Subject> subjectList = IRegistration.getRegistedSubject(currUser.getUserId());
            ArrayList<Quiz> simulationList = quizInterface.getAllSimulationQuizByUser(currUser.getUserId(), subjectSearchId, searchQuizName);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("simulationList", simulationList);
            request.getRequestDispatcher("quizhandle/simulationExam.jsp").forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
