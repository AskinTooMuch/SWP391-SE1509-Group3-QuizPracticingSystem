/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  QuizController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         NamDHHe510519   First Deploy
 */
package controller;

import bean.Answer;
import bean.CustomerQuiz;
import bean.Question;
import bean.QuestionQuizHandle;
import bean.Quiz;
import bean.QuizQuizHandle;
import dao.CustomerQuizINT;
import dao.QuestionINT;
import dao.QuestionQuizHandleINT;
import dao.QuizINT;
import dao.QuizQuizHandleINT;
import dao.impl.CustomerQuizDAO;
import dao.impl.QuestionDAO;
import dao.impl.QuestionQuizHandleDAO;
import dao.impl.QuizDAO;
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
    QuizINT quizINT = new QuizDAO();

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
                    questionArray = quizQHINT.generateQuiz(questionList, quizId);
                }
                session.setAttribute("questionArray", questionArray);
                int quizType = questionArray.getQuiz().getTestTypeId();
                if (quizType == 2) {//1 exam 2 practice
                    session.setMaxInactiveInterval(questionArray.getQuiz()
                            .getQuizDuration());
                } else {
                    session.setMaxInactiveInterval(7200);
                }
                request.setAttribute("quizType", quizType);

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

                //send being taking question's information
                request.setAttribute("answered", questionQH.getAnsweredId());

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
                request.setAttribute("quizSize", quiz.size());
                request.setAttribute("duration", questionArray.getTime());
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
                            && (action.charAt(0) != 'S') && (action.charAt(0) != 'E') && (action.charAt(0) != 'F')) {

                        response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=" + Integer.parseInt(action));
                    }else if (action.equalsIgnoreCase("Finish Exam")) {
                        int time = Integer.parseInt(request.getParameter("time"));
                        questionArray.setTime(time);
                        request.setAttribute("totalsecond", time);
                        response.sendRedirect("quizController?service=quizSummary");
                    }
                } else {
                    request.getRequestDispatcher("quizhandle/quizHandle.jsp").forward(request, response);
                }
            }

            if (service.equalsIgnoreCase("quizSummary")) {
                HttpSession session = request.getSession(true);
                QuizQuizHandle questionArray = null;
                Object object = session.getAttribute("questionArray");
                //co roi
                if (object != null) {
                    questionArray = (QuizQuizHandle) object;
                    request.setAttribute("quizId", questionArray.getQuiz()
                            .getQuizId());
                    request.setAttribute("quizType", questionArray.getQuiz()
                            .getTestTypeId());
                    ArrayList<QuestionQuizHandle> quizSummary = questionArray.getQuestions();
                    request.setAttribute("quizSummary", quizSummary);
                    request.setAttribute("total", questionArray.getTime());
                    request.setAttribute("quizSize", questionArray.getQuestions()
                            .size());
                    int answeredQuestionNumber = quizQHINT.getAnsweredQuestion(questionArray);
                    request.setAttribute("answeredNumber", answeredQuestionNumber);

                    request.getRequestDispatcher("quizhandle/quizSummary.jsp").forward(request, response);
                } else {
                    response.sendRedirect("homeController");
                }

            }

            if (service.equalsIgnoreCase("submit")) {
                HttpSession session = request.getSession(true);
                QuizQuizHandle questionArray = null;
                Object object = session.getAttribute("questionArray");

                if (object != null) {
                    questionArray = (QuizQuizHandle) object;
                    int quizId = questionArray.getQuiz().getQuizId();
                    int time = Integer.parseInt(request.getParameter("time"));
                    //Score of this quiz    
                    double score = quizQHINT.calculateScore(questionArray);
                    //Date of this quiz
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    //Insert into CustomerQuiz table in database
                    CustomerQuiz customerQuiz = new CustomerQuiz(0, quizId, 2, (int) score, time, date, true);
                    CustomerQuizINT customerQuizINT = new CustomerQuizDAO();
                    customerQuizINT.addCustomerQuiz(customerQuiz);
                    //Insert into TakeAnswer table in database;
                    customerQuizINT.addTakeAnswer(questionArray);
                    //Inser into MarkQuestion table in database;
                    customerQuizINT.addMarkQuestion(questionArray);
                    //redirect user to review quiz page
                    session.removeAttribute("questionArray");
                    response.sendRedirect("homeController");
                }
            }

            if (service.equalsIgnoreCase("quizReview")) {
                //prepare quiz information
                int quizTakeId = Integer.parseInt(request.getParameter("quizTakeId"));
                request.setAttribute("quizTakeId", quizTakeId);
                QuizQuizHandle questionArray = quizQHINT.getReviewQuiz(quizTakeId);
                ArrayList<QuestionQuizHandle> quizReview = questionArray.getQuestions();
                request.setAttribute("quizReview", quizReview);
                request.setAttribute("quizSize", quizReview.size());

                int questionNumber;
                if (request.getParameter("questionNumber") == null) {
                    questionNumber = 1;
                } else {
                    questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
                }
                QuestionQuizHandle questionQH = questionArray.getQuestionByNumber(questionNumber);

                //send being reviewing question's information
                request.setAttribute("answered", questionQH.getAnsweredId());
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

                String action = request.getParameter("action");
                if (action != null) {

                    //prepare for next action
                    int newQuestionNumber = 0;

                    //previous question
                    if (action.equalsIgnoreCase("Previous Question")) {
                        newQuestionNumber = --questionNumber;
                        response.sendRedirect("quizController?service=quizReview&quizTakeId=" + quizTakeId + "&questionNumber=" + newQuestionNumber);

                        //next question    
                    } else if (action.equalsIgnoreCase("Next Question")) {
                        newQuestionNumber = ++questionNumber;
                        response.sendRedirect("quizController?service=quizReview&quizTakeId=" + quizTakeId + "&questionNumber=" + newQuestionNumber);

                        //score exam
                    } else if ((action.charAt(0) != 'P') && (action.charAt(0) != 'N')
                            && (action.charAt(0) != 'S')) {

                        response.sendRedirect("quizController?service=quizReview&quizTakeId=" + quizTakeId + "&questionNumber=" + Integer.parseInt(action));
                    } else if (action.equalsIgnoreCase("Finish Review")) {
                        response.sendRedirect("homeController");
                    }
                } else {
                    request.getRequestDispatcher("quizhandle/quizReview.jsp").forward(request, response);
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
