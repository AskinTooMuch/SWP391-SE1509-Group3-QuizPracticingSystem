/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  QuizController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         NamDHHe510519   First Deploy
 *  24/9/21     1.0         NamDHHE150519   Update quiz handle
 *  25/9/21     1.0         NamDHHE150519   Update quiz review
 *  26/9/21     1.0         NamDHHE150519   Update quiz summary
 *  26/9/21     1.1         NamDHHE150519   Update simulation Exam
 *  07/10/21    1.2         TuanPAHE150543  Add service filterQuestion,getFilterInformation
 *  08/10/21    1.2         TuanPAHE150543  Update service filterQuestion ,getFilterInformation
 *  10/10/21    1.3         DuongNHHE150328 Update service getQuizDetailInformation,createQuiz
 */
package controller;

import bean.Answer;
import bean.CustomerQuiz;
import bean.Dimension;
import bean.DimensionType;
import bean.Lesson;
import bean.Question;
import bean.QuestionManage;
import bean.QuestionQuizHandle;
import bean.Quiz;
import bean.QuizLevel;
import bean.QuizQuizHandle;
import bean.Subject;
import bean.TestType;
import bean.User;
import bean.UserRole;
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
import dao.DimensionDAO;
import dao.DimensionTypeDAO;
import dao.LessonDAO;
import dao.QuestionDAO;
import dao.QuestionQuizHandleDAO;
import dao.QuizDAO;
import dao.QuizLevelDAO;
import dao.QuizQuizHandleDAO;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import dao.TestTypeDAO;
import dao.impl.DimensionDAOImpl;
import dao.impl.DimensionTypeDAOImpl;
import dao.impl.LessonDAOImpl;
import dao.impl.QuizLevelDAOImpl;
import dao.impl.RegistrationDAOImpl;
import dao.impl.SubjectDAOImpl;
import dao.impl.TestTypeDAOImpl;
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
    static final int IMAGE_MEDIA_TYPE = 1;
    static final int VIDEO_MEDIA_TYPE = 2;
    static final int EXAM_TYPE_ID = 1;
    static final int PRACTICE_TYPE_ID = 2;
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

            /**
             * Service quiz entrance: create a session for doing quiz
             */
            if (service.equalsIgnoreCase("quizEntrance")) {
                HttpSession session = request.getSession();
                int quizId = Integer.parseInt(request.getParameter("quizId"));
                if (session.getAttribute("doingQuiz") == null) {
                    User user = (User) session.getAttribute("currUser");
                    ArrayList<Question> questionList = questionInterface.getQuestionByQuizId(quizId);
                    QuizQuizHandle doingQuiz = quizQHInterface.generateQuiz(questionList, quizId, user);
                    session.setAttribute("doingQuiz", doingQuiz);
                }
                response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=1");
            }

            /**
             * Service quiz handle: handle with all situation happen in a quiz
             * taking session
             */
            if (service.equalsIgnoreCase("quizHandle")) {
                //get quiz from session or generate new quiz (not yet have userId)
                HttpSession session = request.getSession(true);
                Object object = session.getAttribute("doingQuiz");
                if (object != null) {
                    QuizQuizHandle doingQuiz = (QuizQuizHandle) object;
                    int quizId = doingQuiz.getQuiz().getQuizId();
                    request.setAttribute("quizId", quizId);
                    int quizType = doingQuiz.getQuiz()
                            .getTestTypeId();
                    request.setAttribute("quizType", quizType);

                    ArrayList<QuestionQuizHandle> quiz = doingQuiz.getQuestions();
                    request.setAttribute("quiz", quiz);

                    //get question id
                    int questionNumber;
                    if (request.getParameter("questionNumber") == null) {
                        questionNumber = DEFAULT_PAGE;
                    } else {
                        questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
                    }
                    QuestionQuizHandle questionQH = doingQuiz.getQuestionByNumber(questionNumber);

                    String media = questionQH.getQuestion().getMedia();
                    if (media != null) {
                        int mediaType = VIDEO_MEDIA_TYPE;
                        String[] imageExtensions = {".jpg", ".gif", ".jpeg", ".jfif", ".pjpeg", ".png", ".pjps"};
                        for (String extension : imageExtensions) {
                            if (media.contains(extension)) {
                                mediaType = IMAGE_MEDIA_TYPE;
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
                    String autoSubmit = request.getParameter("autoSubmit");
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
                        //previous question
                        if (action.equalsIgnoreCase("Previous Question")) {
                            response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=" + --questionNumber);

                            //next question    
                        } else if (action.equalsIgnoreCase("Next Question")) {
                            response.sendRedirect("quizController?service=quizHandle&quizId=" + quizId + "&questionNumber=" + ++questionNumber);

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
                        request.setAttribute("doingQuiz", doingQuiz);
                    } else if (autoSubmit != null) {
                        String answerTakenIdRaw = request.getParameter("answerTakenId");
                        String questionTakenNumberRaw = request.getParameter("questionTakenNumber");

                        //set answerId of this question
                        if (answerTakenIdRaw != null && questionTakenNumberRaw != null) {
                            int answerTakenId = Integer.parseInt(answerTakenIdRaw);
                            questionQH.setAnsweredId(answerTakenId);
                        }

                        if (autoSubmit.equalsIgnoreCase("yes")) {
                            doingQuiz = (QuizQuizHandle) object;
                            int time = Integer.parseInt(request.getParameter("time"));
                            doingQuiz.setTime(time);
                            session.removeAttribute("doingQuiz");
                            CustomerQuizDAO customerQuizInterface = new CustomerQuizDAOImpl();
                            int latestTakeQuizId = customerQuizInterface.getLastAddedCustomerQuiz().getQuizTakeId();
                            //redirect user to review quiz page  
                            response.sendRedirect("quizController?service=quizReview&quizTakeId=" + latestTakeQuizId + "&questionNumber=1");
                            return;
                        }
                    } else {
                        request.getRequestDispatcher("quizhandle/quizHandle.jsp").forward(request, response);
                    }

                } else {
                    response.sendRedirect("homeController");
                }
            }
            /**
             * Service quiz summary: review quiz progress while taking a quiz
             */

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
            /**
             * Service quiz review: review the quiz after taking it
             */
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
                long startedAt = customerQuiz.getSubmitedAt().getTime() - customerQuiz.getTime() * 1000;;
                long submitedAt = customerQuiz.getSubmitedAt().getTime();
                Timestamp submitTime = new Timestamp(submitedAt);
                Timestamp startTime = new Timestamp(startedAt);
                String startedAtTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(startTime);
                String submitedAtTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(submitTime);
                request.setAttribute("submitedAt", submitedAtTime);
                request.setAttribute("startedAt", startedAtTime);
                request.setAttribute("score", customerQuiz.getScore());

                int questionNumber;
                if (request.getParameter("questionNumber") == null) {
                    questionNumber = DEFAULT_PAGE;
                } else {
                    questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
                }
                QuestionQuizHandle questionQH = doingQuiz.getQuestionByNumber(questionNumber);
                String media = questionQH.getQuestion().getMedia();
                if (media != null) {
                    int mediaType = VIDEO_MEDIA_TYPE;
                    String[] imageExtensions = {".jpg", ".gif", ".jpeg", ".jfif", ".pjpeg", ".png", ".pjps"};
                    for (String extension : imageExtensions) {
                        if (media.contains(extension)) {
                            mediaType = IMAGE_MEDIA_TYPE;
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
                    //previous question
                    if (action.equalsIgnoreCase("Previous Question")) {
                        response.sendRedirect("quizController?service=quizReview&quizTakeId=" + quizTakeId + "&questionNumber=" + --questionNumber);

                        //next question    
                    } else if (action.equalsIgnoreCase("Next Question")) {
                        response.sendRedirect("quizController?service=quizReview&quizTakeId=" + quizTakeId + "&questionNumber=" + ++questionNumber);

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
            /**
             * Service simulation exam: view all exam quiz avaliable from the
             * subject customer had registered
             */
            if (service.equalsIgnoreCase("simulationExam")) {
                HttpSession session = request.getSession();
                QuizQuizHandle doingQuiz = (QuizQuizHandle) session.getAttribute("doingQuiz");
                if (doingQuiz != null) {
                    request.setAttribute("doingQuiz", doingQuiz);
                }
                RegistrationDAO IRegistration = new RegistrationDAOImpl();
                User currUser = (User) session.getAttribute("currUser");
                String subjectSearchIdRaw = request.getParameter("subjectSearchId");
                int subjectSearchId = 0;
                if (subjectSearchIdRaw != null && !subjectSearchIdRaw.equalsIgnoreCase("")) {
                    subjectSearchId = Integer.parseInt(subjectSearchIdRaw);
                    request.setAttribute("subjectSearchId", +subjectSearchId);
                }

                String searchQuizName = request.getParameter("subjectSearchName");
                ArrayList<Subject> subjectList = IRegistration.getRegistedSubject(currUser.getUserId());
                ArrayList<Quiz> simulationList = quizInterface.getAllSimulationQuizByUser(currUser.getUserId(), subjectSearchId, searchQuizName);

                request.setAttribute("subjectList", subjectList);
                request.setAttribute("simulationList", simulationList);
                request.getRequestDispatcher("quizhandle/simulationExam.jsp").forward(request, response);
            }

            /**
             * Service: Search Question by Content
             */
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
            }

            /**
             * Service: filter Question by subjectId, dimensionId, lessonId
             */
            if (service.equalsIgnoreCase("filterQuestion")) {
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                int lessonId = Integer.parseInt(request.getParameter("lessonId"));
                int dimensionId = Integer.parseInt(request.getParameter("dimensionId"));
                ArrayList<QuestionManage> listQuestionManage = questionInterface.getQuestionManage(subjectId, dimensionId, lessonId);
                request.setAttribute("listQuestionManage", listQuestionManage);
                request.getRequestDispatcher("jsp/questionList.jsp").forward(request, response);
//                out.println(questionManage.size());
            }

            /**
             * Service: get all Subject, Dimension, Lesson Information
             */
            if (service.equalsIgnoreCase("getFilterInformation")) {
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                DimensionDAO dimensionDAO = new DimensionDAOImpl();
                LessonDAO lessonDAO = new LessonDAOImpl();
                ArrayList<Subject> listSubject = subjectDAO.getAllSubjects();
                ArrayList<Dimension> listDimension = dimensionDAO.getAllDimension();
                ArrayList<Lesson> listLesson = lessonDAO.getAllLessons();
                request.getSession().setAttribute("listFilterSubject", listSubject);
                request.getSession().setAttribute("listFilterDimension", listDimension);
                request.getSession().setAttribute("listFilterLesson", listLesson);
                request.getRequestDispatcher("jsp/questionList.jsp").forward(request, response);
            }

            /**
             * Get information to display in the quizDetail page
             */
            if (service.equalsIgnoreCase("getQuizDetailInformation")) {
                User currUser = (User) request.getSession().getAttribute("currUser");
                String message = (String) request.getAttribute("message");
                String role = ((UserRole) request.getSession().getAttribute("role")).getUserRoleName();
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                QuizLevelDAO quizLevelDAO = new QuizLevelDAOImpl();
                TestTypeDAO testTypeDAO = new TestTypeDAOImpl();
                DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAOImpl();
                ArrayList<Subject> subjectList = new ArrayList<>();
                ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllQuizLevel();
                ArrayList<TestType> testTypeList = testTypeDAO.getAllTestTypes();
                ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllDimensionTypes();
                if (role.equalsIgnoreCase("admin")) { //if user is a admin then get all subject
                    subjectList = subjectDAO.getAllSubjects();
                } else if (role.equalsIgnoreCase("expert")) { //if user is a expert then get all asigned subject
                    subjectList = subjectDAO.getSubjectsAssigned(currUser.getUserId());
                }
                request.setAttribute("subjectList", subjectList);
                request.setAttribute("quizLevelList", quizLevelList);
                request.setAttribute("testTypeList", testTypeList);
                request.setAttribute("dimensionTypeList", dimensionTypeList);
                if (message != null) {
                    request.setAttribute("message", message);
                }
                request.getRequestDispatcher("jsp/quizDetail.jsp").forward(request, response);
            }

            /**
             * Get information from quizDetail to create quiz then add to the database
             */
            if (service.equalsIgnoreCase("createQuiz")) {
                // get all parameter that user input
                String quizName = (String) request.getParameter("quizName").trim();
                int subjectId = Integer.parseInt(request.getParameter("subject"));
                int duration = Integer.parseInt(request.getParameter("duration")) * 60;
                int quizLevelId = Integer.parseInt(request.getParameter("examLevel"));
                int passRate = Integer.parseInt(request.getParameter("passRate"));
                int testTypeId = Integer.parseInt(request.getParameter("testType"));
                int numberOfQuestion = Integer.parseInt(request.getParameter("numbetOfQuestion"));
                int dimensionId = Integer.parseInt(request.getParameter("dimensionType"));
                String description = request.getParameter("description").trim();
                QuestionDAO questionDAO = new QuestionDAOImpl();
                SubjectDAO subjectDAO = new SubjectDAOImpl();
                QuizDAO quizDAO = new QuizDAOImpl();
                Quiz createdQuiz = new Quiz();
                ArrayList<Question> questionList = questionDAO.getQuestionForCreateQuiz(numberOfQuestion, subjectId, dimensionId);
                //if quizNmae have yet been enter, return mesaage
                if (quizName.length() == 0) {
                    request.setAttribute("message", "You have to enter quiz name");
                    request.getRequestDispatcher("quizController?service=getQuizDetailInformation")
                            .forward(request, response);
                }
                // if the aren't any question that meet user requirement return message
                if (questionList.size() == 0) {
                    request.setAttribute("message", "There aren't any question that meet your requirement");
                    request.getRequestDispatcher("quizController?service=getQuizDetailInformation")
                            .forward(request, response);
                }
                //prepare quiz information to add to database
                createdQuiz.setSubject(subjectDAO.getSubjectbyId(subjectId));
                createdQuiz.setQuizName(quizName);
                createdQuiz.setQuizLevelId(quizLevelId);
                createdQuiz.setQuizDuration(duration);
                createdQuiz.setPassRate(passRate);
                createdQuiz.setTestTypeId(testTypeId);
                createdQuiz.setDescription(description);
                createdQuiz.setNumberQuestion(numberOfQuestion);
                createdQuiz.setDimensionTypeId(dimensionId);
                quizDAO.addQuiz(createdQuiz);//add quiz to the database
                int quizId = quizDAO.getQuizIdCreated(createdQuiz);
                // add all quiz's questions
                for (Question question : questionList) {
                    quizDAO.addQuizQuestion(quizId, question.getQuestionId());
                }
                request.setAttribute("message", "Add quiz successfull!!(" + questionList.size() +" questions)" );
                request.getRequestDispatcher("quizController?service=getQuizDetailInformation")
                        .forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
