/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  QuizController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         NamDHHe510519   First Deploy
 *  24/9/21     1.1         NamDHHE150519   Update quiz handle
 *  25/9/21     1.2         NamDHHE150519   Update quiz review
 *  26/9/21     1.3         NamDHHE150519   Update quiz summary
 */
package controller;

import bean.Answer;
import bean.CustomerQuiz;
import bean.DimensionType;
import bean.Question;
import bean.QuestionManage;
import bean.QuestionQuizHandle;
import bean.Quiz;
import bean.QuizQuizHandle;
import bean.Subject;
import bean.User;
import dao.impl.CustomerQuizDAOImpl;
import dao.impl.QuestionDAOImpl;
import dao.impl.QuestionQuizHandleDAOImpl;
import dao.impl.QuizDAOImpl;
import dao.impl.QuizQuizHandleDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.CustomerQuizDAO;
import dao.DimensionTypeDAO;
import dao.QuestionDAO;
import dao.QuestionQuizHandleDAO;
import dao.QuizDAO;
import dao.QuizQuizHandleDAO;
import dao.RegistrationDAO;
import dao.impl.DimensionTypeDAOImpl;
import dao.impl.RegistrationDAOImpl;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

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
    static final int EXAM_TYPE = 1;
    static final int PRACTICE_TYPE = 2;
    static final int MAX_SESSION_TIME = 7200;
    static final int DEFAULT_PAGE = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            QuizQuizHandleDAO quizQHInterface = new QuizQuizHandleDAOImpl();
            QuestionQuizHandleDAO questionQHInterface = new QuestionQuizHandleDAOImpl();
            QuestionDAO questionInterface = new QuestionDAOImpl();
            QuizDAO quizInterface = new QuizDAOImpl();
            String service = request.getParameter("service");

            if (service.equalsIgnoreCase("quizEntrance")) {
                int quizId = Integer.parseInt(request.getParameter("quizId"));
                ArrayList<Question> questionList = questionInterface.getQuestionByQuizId(quizId);
                QuizQuizHandle doingQuiz = quizQHInterface.generateQuiz(questionList, quizId);
                HttpSession session = request.getSession(true);
                session.setAttribute("doingQuiz", doingQuiz);
                response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=1");
            }

            if (service.equalsIgnoreCase("quizHandle")) {
                //get quiz from session or generate new quiz (not yet have userId)
                HttpSession session = request.getSession(true);
                Object object = session.getAttribute("doingQuiz");
                if (object != null) {
                    QuizQuizHandle doingQuiz = (QuizQuizHandle) object;
                    int quizId = Integer.parseInt(request.getParameter("quizId"));
                    request.setAttribute("quizId", quizId);
                    int quizType = doingQuiz.getQuiz()
                            .getTestTypeId();
                    request.setAttribute("quizType", quizType);

                    ArrayList<QuestionQuizHandle> quiz = doingQuiz.getQuestions();
                    request.setAttribute("quiz", quiz);

                    //get question id
                    int questionNumber;
                    if (request.getParameter("questionNumber") == null) {
                        questionNumber = 1;
                    } else {
                        questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
                    }
                    QuestionQuizHandle questionQH = doingQuiz.getQuestionByNumber(questionNumber);

                    String media = questionQH.getQuestion().getMedia();
                    if (media != null) {
                        int mediaType = 2;
                        String[] imageExtensions = {".jpg", ".gif", ".jpeg", ".jfif", ".pjpeg", ".png", ".pjps"};
                        for (String extension : imageExtensions) {
                            if (media.contains(extension)) {
                                mediaType = 1;
                            }
                        }
                        request.setAttribute("mediaType", mediaType);
                    }
                    //send being taking question's information
                    request.setAttribute("questionQH", questionQH);
                    //true answer of question
                    Answer trueAnswer = questionQHInterface.getRightAnswer(questionQH);
                    request.setAttribute("trueAnswer", trueAnswer.getAnswerContent());
                    //number of question in this quiz
                    request.setAttribute("questionNumber", questionNumber);

                    //Mark this question 
                    String marked = request.getParameter("marked");
                    if (marked != null && marked.equalsIgnoreCase("yes")) {
                        questionQHInterface.markQuestion(questionQH);
                    }

                    //send quiz infomation
                    //Number of answered question in quiz
                    int answeredQuestionNumber = quizQHInterface.getAnsweredQuestion(doingQuiz);
                    request.setAttribute("answeredNumber", answeredQuestionNumber);
                    //length of this quiz
                    request.setAttribute("duration", doingQuiz.getQuiz().getQuizDuration());
                    //Next quiz, Previous quiz, Score Exams handle
                    String action = request.getParameter("action");
                    String finalAction = request.getParameter("finalAction");
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
                        } else if (action.equalsIgnoreCase("Finish Exam")) {
                            int time = Integer.parseInt(request.getParameter("time"));
                            doingQuiz.setTime(time);
                            request.setAttribute("totalsecond", time);
                            response.sendRedirect("quizController?service=quizSummary");
                        }
                    } else if (finalAction != null) {
                        String answerTakenIdRaw = request.getParameter("answerTakenId");
                        String questionTakenNumberRaw = request.getParameter("questionTakenNumber");

                        //set answerId of this question
                        if (answerTakenIdRaw != null && questionTakenNumberRaw != null) {
                            int answerTakenId = Integer.parseInt(answerTakenIdRaw);
                            questionQH.setAnsweredId(answerTakenId);
                        }

                        if (finalAction.equalsIgnoreCase("Finish Exam")) {
                            int time = Integer.parseInt(request.getParameter("time"));
                            doingQuiz.setTime(time);
                            request.setAttribute("totalsecond", time);
                            response.sendRedirect("quizController?service=quizSummary");
                        }
                    } else {
                        request.getRequestDispatcher("quizhandle/quizHandle.jsp").forward(request, response);
                    }

                } else {
                    response.sendRedirect("homeController");
                }
            }

            if (service.equalsIgnoreCase("quizSummary")) {
                HttpSession session = request.getSession();
                QuizQuizHandle doingQuiz = null;
                Object object = session.getAttribute("doingQuiz");
                //co roi
                if (object != null) {
                    doingQuiz = (QuizQuizHandle) object;
                    request.setAttribute("doingQuiz", doingQuiz);
                    request.setAttribute("quizType", doingQuiz.getQuiz().getTestTypeId());
                    request.setAttribute("total", doingQuiz.getTime());
                    int answeredQuestionNumber = quizQHInterface.getAnsweredQuestion(doingQuiz);
                    request.setAttribute("answeredNumber", answeredQuestionNumber);
                    request.getRequestDispatcher("quizhandle/quizSummary.jsp").forward(request, response);
                } else {
                    response.sendRedirect("homeController");
                }
            }

            if (service.equalsIgnoreCase("submit")) {
                HttpSession session = request.getSession(true);
                QuizQuizHandle doingQuiz = null;
                Object object = session.getAttribute("doingQuiz");
                int latestTakeQuizId = 0;
                if (object != null) {
                    doingQuiz = (QuizQuizHandle) object;
                    int time = Integer.parseInt(request.getParameter("time"));
                    doingQuiz.setTime(time);
                    session.removeAttribute("doingQuiz");
                    CustomerQuizDAO customerQuizInterface = new CustomerQuizDAOImpl();
                    latestTakeQuizId = customerQuizInterface.getLastAddedCustomerQuiz().getQuizTakeId();
                    //redirect user to review quiz page  
                    response.sendRedirect("quizController?service=quizReview&quizTakeId=" + latestTakeQuizId + "&questionNumber=1");
                    return;
                }
            }

            if (service.equalsIgnoreCase("quizReview")) {
                //prepare quiz information
                int quizTakeId = Integer.parseInt(request.getParameter("quizTakeId"));
                request.setAttribute("quizTakeId", quizTakeId);
                CustomerQuizDAO customerQuizInterface = new CustomerQuizDAOImpl();
                QuizDAO quizDAOInterface = new QuizDAOImpl();
                QuizQuizHandle doingQuiz = quizQHInterface.getReviewQuiz(quizTakeId);
                ArrayList<QuestionQuizHandle> quizReview = doingQuiz.getQuestions();
                Quiz quiz = quizDAOInterface.getQuizByQuizTakeId(quizTakeId);
                request.setAttribute("quizReview", quizReview);
                request.setAttribute("quizSize", quizReview.size());

                CustomerQuiz customerQuiz = customerQuizInterface.getLastAddedCustomerQuiz();
                long startedAt = 0;
                long submitedAt = 0;
                if (quiz.getTestTypeId() == 1) {
                    startedAt = customerQuiz.getStartedAt().getTime() - (quiz.getQuizDuration() - customerQuiz.getTime()) * 1000;
                } else {
                    startedAt = customerQuiz.getStartedAt().getTime() - customerQuiz.getTime() * 1000;
                }
                submitedAt = customerQuiz.getStartedAt().getTime();
                Timestamp submitTime = new Timestamp(submitedAt);
                Timestamp startTime = new Timestamp(startedAt);
                String startedAtTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(startTime);
                String submitedAtTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(submitTime);
                request.setAttribute("submitedAt", submitedAtTime);
                request.setAttribute("startedAt", startedAtTime);
                request.setAttribute("score", customerQuiz.getScore());

                int questionNumber;
                if (request.getParameter("questionNumber") == null) {
                    questionNumber = 1;
                } else {
                    questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
                }
                QuestionQuizHandle questionQH = doingQuiz.getQuestionByNumber(questionNumber);
                String media = questionQH.getQuestion().getMedia();
                if (media != null) {
                    int mediaType = 2;
                    String[] imageExtensions = {".jpg", ".gif", ".jpeg", ".jfif", ".pjpeg", ".png", ".pjps"};
                    for (String extension : imageExtensions) {
                        if (media.contains(extension)) {
                            mediaType = 1;
                        }
                    }
                    request.setAttribute("mediaType", mediaType);
                }
                //send being reviewing question's information
                request.setAttribute("answered", questionQH.getAnsweredId());
                request.setAttribute("questionQH", questionQH);
                String questionContent = questionQH.getQuestion().getContent();
                request.setAttribute("questionContent", questionContent);
                //answer List of question
                ArrayList<Answer> answerList = questionQH.getAnswerList();
                request.setAttribute("answerList", answerList);
                //true answer of question
                Answer trueAnswer = questionQHInterface.getRightAnswer(questionQH);
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

            if (service.equalsIgnoreCase("searchQuestionByContent")) {
                String content = request.getParameter("content").trim();
                ArrayList<QuestionManage> listQuestionManage = new ArrayList<>();
                if (content.length() == 0) {
                    listQuestionManage = questionInterface.getQuestionByContent(null);
                } else {
                    listQuestionManage = questionInterface.getQuestionByContent(content);
                }
                request.setAttribute("listQuestionManage", listQuestionManage);
                request.getRequestDispatcher("jsp/questionList.jsp").forward(request, response);

//                sendDispatcher(request, response, "jsp/questionList.jsp");


            }   
                
            if (service.equalsIgnoreCase("getPracticeDetail")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                RegistrationDAO registrationDAO = new RegistrationDAOImpl();
                DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAOImpl();
                ArrayList<Subject> registedSubject = registrationDAO.getRegistedSubject(currUser.getUserId());
                ArrayList<DimensionType> dimensionTypes = dimensionTypeDAO.getAllDimensionTypes();
                request.setAttribute("registedSubject", registedSubject);
                request.setAttribute("dimensionTypes", dimensionTypes);
                request.getRequestDispatcher("jsp/practiceDetail.jsp").forward(request, response);
            }
            
            if(service.equalsIgnoreCase("createPractice")){
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int numberOfQuestion = Integer.parseInt(request.getParameter("numberOfQuestion"));
                int dimensionId = Integer.parseInt(request.getParameter("dimension"));
                int duration = Integer.parseInt(request.getParameter("duration"));
                QuestionDAO questionDAO = new QuestionDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                ArrayList<Question> questionList = questionDAO.getQuestionForCreateQuiz(numberOfQuestion, subjectId, dimensionId);
                Quiz quiz = new Quiz();
                quiz.setSubjectId(subjectId);
                quiz.setQuizDuration(duration*60);
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
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            response.sendRedirect("error.jsp");
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
