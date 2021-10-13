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

            //Get all information to display in the practiceDetail page
            if (service.equalsIgnoreCase("getPracticeDetail")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                RegistrationDAO registrationDAO = new RegistrationDAOImpl();
                DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAOImpl();
                //Get all subject that user have registed
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(currUser.getUserId());
                ArrayList<DimensionType> dimensionTypes = dimensionTypeDAO.getAllDimensionTypes();
                request.getSession().setAttribute("registedSubject", registedSubject);
                request.getSession().setAttribute("dimensionTypes", dimensionTypes);
                request.getRequestDispatcher("jsp/practiceDetail.jsp").forward(request, response);
            }

            //create quiz that meet user's requirement
            if (service.equalsIgnoreCase("createPractice")) {
                //Get user's input values
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
                int dimensionId = Integer.parseInt(request.getParameter("dimension"));
                int duration = Integer.parseInt(request.getParameter("duration"));
                QuestionDAO questionDAO = new QuestionDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                //select question that meet user requirement
                ArrayList<Question> questionList = questionDAO.getQuestionForCreateQuiz(numberOfQuestion, subjectId, dimensionId);

                // if there isn't any question meet user requirement then display message
                if (questionList.size() == 0) {
                    String mess = "There isn't any question that meet your require!";
                    request.setAttribute("message", mess);
                    request.getRequestDispatcher("jsp/practiceDetail.jsp").forward(request, response);
                    return;
                }
                
                //setup quiz information to create new ppractice
                Quiz quiz = new Quiz();
                Subject subject = subjectDAO.getSubjectbyId(subjectId);
                quiz.setQuizName("Practice Quiz");
                quiz.setSubject(subject);
                quiz.setQuizDuration(duration * 60);
                quiz.setTestTypeId(3);
                quiz.setNumberQuestion(questionList.size());
                quiz.setDimensionTypeId(dimensionId);
                quiz.setStatus(true);
                quizDAO.addQuiz(quiz);// add practice
                // add practice questions
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
                //get all quiz of the user
                ArrayList<CustomerQuiz> customerQuizs = customerQuizDAO.getQuizByUser(currUser.getUserId());
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(currUser.getUserId());
                request.setAttribute("registedSubject", registedSubject);
                //if user not enter any thing return all quiz
                if (subjectId == 0) {
                    request.setAttribute("customerQuizs", customerQuizs);
                    request.getRequestDispatcher("jsp/practiceList.jsp").forward(request, response);
                    return;
                } else {
                    //filer quiz by userId
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
                    //if user don't enter anything then get all quiz
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
                //get all quiz that have the same subject and test type
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
                if (!customerQuizDAO.checkTeakedQuiz(quizId)) { // if this test haven't been taken then allow change
                    if (editTYpe.equalsIgnoreCase("update")) {//if admin want to edit quiz
                        //get information to display in the update page
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
                    } else if (editTYpe.equalsIgnoreCase("delete")) {//if admin want to delete quiz
                        quizDAO.removeQuizQuestion(quizId);//delete all quiz's questions
                        quizDAO.deleteQuiz(quizId);//delete quiz
                        request.setAttribute("message", "Update quiz successfull!!");
                        request.getRequestDispatcher("practiceController?service=getQuizListInformation")
                                .forward(request, response);
                    }
                } else {// if this test have already been taken then don't allow change
                    ArrayList<Quiz> quizList = quizDAO.getAllQuiz();
                    request.setAttribute("quizQuizList", quizList);
                    request.setAttribute("message", "You can't change this quiz!");
                    request.getRequestDispatcher("jsp/quizList.jsp").forward(request, response);
                }

            }

            //edit quiz information then update to the database
            if (service.equalsIgnoreCase("updateQuizInformation")) {
                //get all parameter from updateQuiz page
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
                //if quizName have yet been enter, return mesaage
                if (quizName.length() == 0) {
                    request.setAttribute("message", "You have to enter quiz name");
                    request.getRequestDispatcher("practiceController?service=getQuizListInformation")
                            .forward(request, response);
                }
                // setup quiz information to edit
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
