/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  08/10/21    1.0         DuongNHHE150328 First Deploy
 *  08/10/21    1.1         DuongNHHE150328 Add service
 *  11/10/21    1.2         DuongNHHE150328 Add service
 */
package controller;

import bean.DimensionType;
import bean.Question;
import bean.Quiz;
import bean.Subject;
import bean.User;
import bean.CustomerQuiz;
import bean.QuizLevel;
import bean.TestType;
import dao.CustomerQuizDAO;
import dao.DimensionTypeDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import dao.QuizLevelDAO;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.TestTypeDAO;
import dao.impl.CustomerQuizDAOImpl;
import dao.impl.DimensionTypeDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.QuizDAOImpl;
import dao.impl.QuizLevelDAOImpl;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.TestTypeDAOImpl;
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

            //Get information to display in the quizList page
            if (service.equalsIgnoreCase("getQuizListInformation")) {
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                TestTypeDAO testTypeDAO = new TestTypeDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                ArrayList<Subject> subjectList = subjectDAO.getAllSubjects();
                ArrayList<TestType> testTypeList = testTypeDAO.getAllTestTypes();
                ArrayList<Quiz> quizList = quizDAO.getAllQuiz();
                String message = request.getParameter("message");
                request.getSession().setAttribute("subjectQuizList", subjectList);
                request.getSession().setAttribute("testTypeQuizList", testTypeList);
                request.setAttribute("quizQuizList", quizList);
                if (message != null) {
                    request.setAttribute("message", message);
                }
                request.getRequestDispatcher("jsp/quizList.jsp").forward(request, response);
            }

            //Get all quiz that have name contain search value
            if (service.equalsIgnoreCase("searchQuizByName")) {
                String quizName = request.getParameter("quizName").trim();
                QuizDAO quizDAO = new QuizDAOImpl();
                ArrayList<Quiz> quizList = new ArrayList<>();
                if (quizName.length() == 0) {
                    quizList = quizDAO.getQuizByName(null);
                } else {
                    quizList = quizDAO.getQuizByName(quizName);
                }
                request.setAttribute("quizQuizList", quizList);
                request.getRequestDispatcher("jsp/quizList.jsp").forward(request, response);
            }

            //Filter quiz according to user requirement
            if (service.equalsIgnoreCase("filterQuiz")) {
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                int testTypeId = Integer.parseInt(request.getParameter("testTypeId"));
                QuizDAO quizDAO = new QuizDAOImpl();
                ArrayList<Quiz> quizList = quizDAO.getFilteredQuiz(subjectId, testTypeId);
                request.setAttribute("quizQuizList", quizList);
                request.getRequestDispatcher("jsp/quizList.jsp").forward(request, response);
            }

            //if quiz can be editted redirect user to update page or delete quiz from database 
            if (service.equalsIgnoreCase("editQuiz")) {
                int quizId = Integer.parseInt(request.getParameter("quizId"));
                String editTYpe = request.getParameter("type");
                CustomerQuizDAO customerQuizDAO = new CustomerQuizDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                if (!customerQuizDAO.checkTeakedQuiz(quizId)) {
                    if (editTYpe.equalsIgnoreCase("update")) {
                        SubjectDAO subjectDAO = new SubjectDAOImpl();
                        QuizLevelDAO quizLevelDAO = new QuizLevelDAOImpl();
                        TestTypeDAO testTypeDAO = new TestTypeDAOImpl();
                        DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAOImpl();
                        Quiz updateQuiz = quizDAO.getQuizById(quizId);
                        ArrayList<Subject> subjectList = subjectDAO.getAllSubjects();
                        ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllQuizLevel();
                        ArrayList<TestType> testTypeList = testTypeDAO.getAllTestTypes();
                        ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllDimensionTypes();
                        request.setAttribute("subjectList", subjectList);
                        request.setAttribute("quizLevelList", quizLevelList);
                        request.setAttribute("testTypeList", testTypeList);
                        request.setAttribute("dimensionTypeList", dimensionTypeList);
                        request.setAttribute("updateQuiz", updateQuiz);
                        request.getRequestDispatcher("jsp/updateQuiz.jsp").forward(request, response);
                    } else if (editTYpe.equalsIgnoreCase("delete")) {
                        quizDAO.removeQuizQuestion(quizId);
                        quizDAO.deleteQuiz(quizId);
                        request.setAttribute("message", "Update quiz successfull!!");
                        request.getRequestDispatcher("practiceController?service=getQuizListInformation")
                                .forward(request, response);
                    }
                } else {
                    ArrayList<Quiz> quizList = quizDAO.getAllQuiz();
                    request.setAttribute("quizQuizList", quizList);
                    request.setAttribute("message", "You can't change this quiz!");
                    request.getRequestDispatcher("jsp/quizList.jsp").forward(request, response);
                }

            }

            //edit quiz information then update to the database
            if (service.equalsIgnoreCase("updateQuizInformation")) {
                String quizName = (String) request.getParameter("quizName").trim();
                int updateQuizId = Integer.parseInt(request.getParameter("updateQuizId"));
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int duration = Integer.parseInt(request.getParameter("duration")) * 60;
                int quizLevelId = Integer.parseInt(request.getParameter("examLevel"));
                int passRate = Integer.parseInt(request.getParameter("passRate"));
                int testTypeId = Integer.parseInt(request.getParameter("testType"));
                int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
                int dimensionId = Integer.parseInt(request.getParameter("dimensionType"));
                String description = request.getParameter("description").trim();
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                Quiz updateQuiz = quizDAO.getQuizById(updateQuizId);
                //if quizNmae have yet been enter, return mesaage
                if (quizName.length() == 0) {
                    request.setAttribute("message", "You have to enter quiz name");
                    request.getRequestDispatcher("practiceController?service=getQuizListInformation")
                            .forward(request, response);
                }
                updateQuiz.setSubject(subjectDAO.getSubjectbyId(subjectId));
                updateQuiz.setQuizName(quizName);
                updateQuiz.setQuizLevelId(quizLevelId);
                updateQuiz.setQuizDuration(duration);
                updateQuiz.setPassRate(passRate);
                updateQuiz.setTestTypeId(testTypeId);
                updateQuiz.setDescription(description);
                updateQuiz.setNumberQuestion(numberOfQuestion);
                updateQuiz.setDimensionTypeId(dimensionId);
                quizDAO.editQuiz(updateQuiz.getQuizId(), updateQuiz);
                request.setAttribute("message", "Update quiz successfull!!");
                request.getRequestDispatcher("practiceController?service=getQuizListInformation")
                        .forward(request, response);
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
