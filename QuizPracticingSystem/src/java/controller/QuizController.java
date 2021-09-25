/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Answer;
import bean.Question;
import bean.QuestionQuizHandle;
import bean.QuizQuizHandle;
import dao.QuestionINT;
import dao.QuestionQuizHandleINT;
import dao.QuizQuizHandleINT;
import dao.impl.QuestionDAO;
import dao.impl.QuestionQuizHandleDAO;
import dao.impl.QuizQuizHandleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

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
                HttpSession session = request.getSession(true);
                QuizQuizHandle questionArray = (QuizQuizHandle) session.getAttribute("questionArray");
                if (questionArray == null) {
                    ArrayList<Question> questionList = questionINT.getAllQuestion();
                    questionArray = quizQHINT.generateQuiz(questionList);
                }
                ArrayList<QuestionQuizHandle> quiz = questionArray.getQuestions();
                session.setAttribute("questionArray", questionArray);//Toan bo cau hoi
                request.setAttribute("quiz", quiz);
                int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));

                QuestionQuizHandle questionQH = questionArray.getQuestionByNumber(questionNumber);
                if (questionQH.getAnsweredId() != 0) {
                    request.setAttribute("answered", questionQH.getAnsweredId());
                }
                request.setAttribute("questionQH", questionQH);//cau hoi  

                String questionContent = questionQH.getQuestion().getContent();
                request.setAttribute("questionContent", questionContent);//noi dung cau hoi

                String marked = request.getParameter("marked");
                if (marked != null && marked.equalsIgnoreCase("yes")) {
                    if (questionQH.isMarked()) {
                        questionQH.setMarked(false);
                    } else {
                        questionQH.setMarked(true);
                    }
                }

                ArrayList<Answer> answerList = questionQH.getAnswerList();
                for (Answer answer : answerList) {//peek at answer
                    if (answer.isIsCorrect()) {
                        request.setAttribute("trueAnswer", answer.getAnswerContent());
                    }
                }

                request.setAttribute("answerList", answerList);//danh sach dap an cua cau hoi  
                request.setAttribute("questionNumber", questionNumber);//so thu tu cua cau hoi
                request.setAttribute("quizSize", questionArray.getQuestions().size());//do dai bai quiz
                request.setAttribute("questionId", questionQH.getQuestion().getQuestionId());//id cau hoi trong database
                request.setAttribute("explanation", questionQH.getQuestion().getExplanation());
                String action = request.getParameter("action");
                if (action != null) {
                    String answerTakenIdRaw = request.getParameter("answerTakenId");
                    String questionTakenNumberRaw = request.getParameter("questionTakenNumber");

                    if (answerTakenIdRaw != null && questionTakenNumberRaw != null) {
                        int answerTakenId = Integer.parseInt(answerTakenIdRaw);
                        questionQH.setAnsweredId(answerTakenId);
                    }
                    int newQuestionNumber = 0;
                    if (action.equalsIgnoreCase("Previous Question")) {
                        newQuestionNumber = --questionNumber;
                        response.sendRedirect("quizController?service=quizHandle&questionNumber=" + newQuestionNumber);
                    } else if (action.equalsIgnoreCase("Next Question")) {
                        newQuestionNumber = ++questionNumber;
                        response.sendRedirect("quizController?service=quizHandle&questionNumber=" + newQuestionNumber);
                    } else if (action.equalsIgnoreCase("Score Exam")){
                        
                    }
                    
                } else {
                    request.getRequestDispatcher("quizhandle/quizHandle.jsp").forward(request, response);
                }

            }
        }
    }

    public static String Timer(int hours, int minutes, int seconds, boolean countUp) {
        String time = "";
        time = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        return time;
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
