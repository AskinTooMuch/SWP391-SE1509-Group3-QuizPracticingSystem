/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  08/10/21    1.0         DuongNHHE150328 First Deploy
 *  08/10/21    1.0         DuongNHHE150328 Add service
 */
package controller;

import bean.DimensionType;
import bean.Question;
import bean.Quiz;
import bean.Subject;
import bean.User;
import bean.CustomerQuiz;
import dao.CustomerQuizDAO;
import dao.DimensionTypeDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.impl.CustomerQuizDAOImpl;
import dao.impl.DimensionTypeDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.QuizDAOImpl;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class PracticeController extends HttpServlet {

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
            //get all user registed subject
            String service = request.getParameter("service");

            if (service.equalsIgnoreCase("getPracticeDetail")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                RegistrationDAO registrationDAO = new RegistrationDAOImpl();
                DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAOImpl();
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(currUser.getUserId());
                ArrayList<DimensionType> dimensionTypes = dimensionTypeDAO.getAllDimensionTypes();
                request.getSession().setAttribute("registedSubject", registedSubject);
                request.getSession().setAttribute("dimensionTypes", dimensionTypes);
                request.getRequestDispatcher("jsp/practiceDetail.jsp").forward(request, response);
            }

            //create quiz that meet user's requirement
            if (service.equalsIgnoreCase("createPractice")) {
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
                int dimensionId = Integer.parseInt(request.getParameter("dimension"));
                int duration = Integer.parseInt(request.getParameter("duration"));
                QuestionDAO questionDAO = new QuestionDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                ArrayList<Question> questionList = questionDAO.getQuestionForCreateQuiz(numberOfQuestion, subjectId, dimensionId);

                // if there isn't any question meet user requirement then display message
                if (questionList.size() == 0) {
                    String mess = "There isn't any question that meet your require!";
                    request.setAttribute("message", mess);
                    request.getRequestDispatcher("jsp/practiceDetail.jsp").forward(request, response);
                    return;
                }

                Quiz quiz = new Quiz();
                Subject subject = subjectDAO.getSubjectbyId(subjectId);
                quiz.setQuizName("Practice Quiz");
                quiz.setSubject(subject);
                quiz.setQuizDuration(duration * 60);
                quiz.setTestTypeId(3);
                quiz.setNumberQuestion(questionList.size());
                quiz.setDimensionTypeId(dimensionId);
                quiz.setStatus(true);
                quizDAO.addQuiz(quiz);
                Quiz practice = quizDAO.getQuizById(quizDAO.getQuizIdCreated(quiz));
                for (Question question : questionList) {
                    quizDAO.addQuizQuestion(practice.getQuizId(), question.getQuestionId());
                }
                response.sendRedirect("quizController?service=quizEntrance&quizId=" + practice.getQuizId());
            }

            //Get information to display in the practiceList
            if (service.equalsIgnoreCase("getPracticeListInformation")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                RegistrationDAO registrationDAO = new RegistrationDAOImpl();
                CustomerQuizDAO customerQuizDAO = new CustomerQuizDAOImpl();
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(currUser.getUserId());
                ArrayList<CustomerQuiz> customerQuizs = customerQuizDAO.getQuizByUser(currUser.getUserId());
                request.setAttribute("registedSubject", registedSubject);
                request.setAttribute("customerQuizs", customerQuizs);
                request.getRequestDispatcher("jsp/practiceList.jsp").forward(request, response);
            }

            //update practice list information
            if (service.equalsIgnoreCase("filterPracticeListInformation")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                RegistrationDAO registrationDAO = new RegistrationDAOImpl();
                CustomerQuizDAO customerQuizDAO = new CustomerQuizDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                ArrayList<CustomerQuiz> filteredCustomerQuizs = new ArrayList<>();
                ArrayList<CustomerQuiz> customerQuizs = customerQuizDAO.getQuizByUser(currUser.getUserId());
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(currUser.getUserId());
                request.setAttribute("registedSubject", registedSubject);
                if (subjectId == 0) {
                    request.setAttribute("customerQuizs", customerQuizs);
                    request.getRequestDispatcher("jsp/practiceList.jsp").forward(request, response);
                    return;
                } else {
                    for (CustomerQuiz customerQuiz : customerQuizs) {
                        if (quizDAO.getQuizById(customerQuiz.getQuizId()).getSubject().getSubjectId() == subjectId) {
                            filteredCustomerQuizs.add(customerQuiz);
                        }
                    }
                    request.setAttribute("customerQuizs", filteredCustomerQuizs);
                    request.getRequestDispatcher("jsp/practiceList.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
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
