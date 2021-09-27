/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Answer;
import bean.CustomerQuiz;
import bean.Question;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;
import dao.CustomerQuizINT;
import dao.QuestionINT;
import dao.QuestionQuizHandleINT;
import dao.QuizQuizHandleINT;
import dao.impl.CustomerQuizDAO;
import dao.impl.QuestionDAO;
import dao.impl.QuestionQuizHandleDAO;
import dao.impl.QuizQuizHandleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class QuizController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    QuizQuizHandleINT quizQHINT = new QuizQuizHandleDAO();
    QuestionQuizHandleINT questionQHINT = new QuestionQuizHandleDAO();
    QuestionINT questionINT = new QuestionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");

            if (service.equalsIgnoreCase("quizHandle")) {
                //get quiz from session or generate new quiz (not yet have userId)
                HttpSession session = request.getSession(true);
                QuizQuizHandle questionArray = (QuizQuizHandle) session.getAttribute("questionArray");
                int quizId = Integer.parseInt(request.getParameter("quizId"));
                request.setAttribute("quizId", quizId);
                if (questionArray == null) {
                    ArrayList<Question> questionList = questionINT.getQuestionByQuizId(quizId);
                    questionArray = quizQHINT.generateQuiz(questionList);
                }
                session.setAttribute("questionArray", questionArray);
                session.setMaxInactiveInterval(3600);
                ArrayList<QuestionQuizHandle> quiz = questionArray.getQuestions();
                request.setAttribute("quiz", quiz);

                //get question id
                int questionNumber;
                if (request.getParameter("questionNumber") == null) {
                    questionNumber = 1;
                } else {
                    questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
                }
                QuestionQuizHandle questionQH = questionArray.getQuestionByNumber(questionNumber);

                //send taken question information
                if (questionQH.getAnsweredId() != 0) {
                    request.setAttribute("answered", questionQH.getAnsweredId());
                }
                request.setAttribute("questionQH", questionQH);
                String questionContent = questionQH.getQuestion().getContent();
                request.setAttribute("questionContent", questionContent);
                //answer List of question
                ArrayList<Answer> answerList = questionQH.getAnswerList();
                request.setAttribute("answerList", answerList);
                //true answer of question
                Answer trueAnswer = questionQHINT.getRightAnswer(questionQH);
                request.setAttribute("trueAnswer", trueAnswer.getAnswerContent());
                //number of question in this quiz
                request.setAttribute("questionNumber", questionNumber);
                //question id in database
                request.setAttribute("questionId", questionQH.getQuestion()
                        .getQuestionId());
                //explanation of this question
                request.setAttribute("explanation", questionQH.getQuestion()
                        .getExplanation());
                //Mark this question 
                String marked = request.getParameter("marked");
                if (marked != null && marked.equalsIgnoreCase("yes")) {
                    questionQHINT.markQuestion(questionQH);
                }

                //send quiz infomation
                //Number of answered question in quiz
                int answeredQuestionNumber = quizQHINT.getAnsweredQuestion(questionArray);
                request.setAttribute("answeredNumber", answeredQuestionNumber);
                //length of this quiz
                request.setAttribute("quizSize", questionArray.getQuestions()
                        .size());

                //Next quiz, Previous quiz, Score Exams handle
                String action = request.getParameter("action");
                if (action != null) {
                    //information of recently submit question include questionNumber in this quiz and answer id in database
                    String answerTakenIdRaw = request.getParameter("answerTakenId");
                    String questionTakenNumberRaw = request.getParameter("questionTakenNumber");

                    //set answerId of this question
                    if (answerTakenIdRaw != null && questionTakenNumberRaw != null) {
                        int answerTakenId = Integer.parseInt(answerTakenIdRaw);
                        questionQH.setAnsweredId(answerTakenId);
                    }

                    //prepare for next action
                    int newQuestionNumber = 0;

                    //previous question
                    if (action.equalsIgnoreCase("Previous Question")) {
                        newQuestionNumber = --questionNumber;
                        response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=" + newQuestionNumber);

                        //next question    
                    } else if (action.equalsIgnoreCase("Next Question")) {
                        newQuestionNumber = ++questionNumber;
                        response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=" + newQuestionNumber);

                        //score exam
                    } else if ((action.charAt(0) != 'P') && (action.charAt(0) != 'N')
                                             && (action.charAt(0) != 'S')) {
                        
                        response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=" + Integer.parseInt(action));
                    } else if (action.equalsIgnoreCase("Score Exam")) {
                        session.removeAttribute("questionArray");
                        //Score of this quiz    
                        double score = quizQHINT.calculateScore(questionArray);
                        //Date of this quiz
                        long millis = System.currentTimeMillis();
                        java.sql.Date date = new java.sql.Date(millis);
                        //Insert into CustomerQuiz table in database
                        int userId = Integer.parseInt(request.getParameter("userId"));
                        CustomerQuiz customerQuiz = new CustomerQuiz(0, quizId, userId, (int) score, date, true);
                        CustomerQuizINT customerQuizINT = new CustomerQuizDAO();
                        customerQuizINT.addCustomerQuiz(customerQuiz);
                        //Insert into TakeAnswer table in database;
                        customerQuizINT.addTakeAnswer(questionArray);
                        //redirect user to review quiz page
                        response.sendRedirect("homeController");
                    }
                } else {
                    request.getRequestDispatcher("quizhandle/quizHandle.jsp").forward(request, response);
                }
            }

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
