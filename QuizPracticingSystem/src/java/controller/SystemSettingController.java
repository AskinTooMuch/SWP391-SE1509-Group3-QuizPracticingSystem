/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 27, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  27/10/21    1.0         DuongNHHE150328 First Deploy
 *  27/10/21    1.1         DuongNHHE150328 Add service
 */
package controller;

import bean.*;
import dao.DimensionTypeDAO;
import dao.LessonTypeDAO;
import dao.PostCateDAO;
import dao.QuizLevelDAO;
import dao.SubjectCateDAO;
import dao.TestTypeDAO;
import dao.UserRoleDAO;
import dao.impl.DimensionTypeDAOImpl;
import dao.impl.LessonTypeDAOImpl;
import dao.impl.PostCateDAOImpl;
import dao.impl.QuizLevelDAOImpl;
import dao.impl.SubjectCateDAOImpl;
import dao.impl.TestTypeDAOImpl;
import dao.impl.UserRoleDAOImpl;
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
 * This class has the process request display or update system settings
 * 
 * @author DuongNHHE150328
 */
public class SystemSettingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * Function Setting List: Display all system setting
     * Function Setting Detail: Allow admin to edit or add new system settings
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
            String service = request.getParameter("service");
            UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
            PostCateDAO postCateDAO = new PostCateDAOImpl();
            SubjectCateDAO subjectCateDAO = new SubjectCateDAOImpl();
            TestTypeDAO testTypeDAO = new TestTypeDAOImpl();
            QuizLevelDAO quizLevelDAO = new QuizLevelDAOImpl();
            LessonTypeDAO lessonTypeDAO = new LessonTypeDAOImpl();
            DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAOImpl();
            
            /**
             * get information to display in the setting list page
             */
            if(service.equalsIgnoreCase("getInformation")){
                //get all user's roles
                ArrayList<UserRole> userRoleList = userRoleDAO.getAllStatusUserRole();
                //get all post's categories
                ArrayList<PostCate> postCateList = postCateDAO.getAllStatusPostCates();
                //get all subject's categories
                ArrayList<SubjectCate> subjectCateList = subjectCateDAO.getAllStatusSubjectCates();
                //get all test's type
                ArrayList<TestType> testTypeList = testTypeDAO.getAllStatusTestTypes();
                //get all quiz's level
                ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllStatusQuizLevel();
                //get all lesson's type
                ArrayList<LessonType> lessonTypeList = lessonTypeDAO.getAllStatusLessonType();
                //get all dimesion's type
                ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllStatusDimensionTypes();
                String message = request.getParameter("message");
                if (message != null) {//if there is a message
                    request.setAttribute("message", message);
                }
                request.setAttribute("userRoleList", userRoleList);
                request.setAttribute("postCateList", postCateList);
                request.setAttribute("subjectCateList", subjectCateList);
                request.setAttribute("testTypeList", testTypeList);
                request.setAttribute("quizLevelList", quizLevelList);
                request.setAttribute("lessonTypeList", lessonTypeList);
                request.setAttribute("dimensionTypeList", dimensionTypeList);
                request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
            }
            
            /**
             * Filter setting list by type of system setting.
             */
            if(service.equalsIgnoreCase("filter")){
                String field = request.getParameter("field");
                if (field.equalsIgnoreCase("userRole")) { // if admin  want to get all user role list
                    ArrayList<UserRole> userRoleList = userRoleDAO.getAllStatusUserRole();
                    request.setAttribute("userRoleList", userRoleList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                }else if (field.equalsIgnoreCase("postCate")) {// if admin only want to get all post category list
                    ArrayList<PostCate> postCateList = postCateDAO.getAllStatusPostCates();
                    request.setAttribute("postCateList", postCateList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                }else if (field.equalsIgnoreCase("subjectCate")) {// if admin only want to get all subject category list
                    ArrayList<SubjectCate> subjectCateList = subjectCateDAO.getAllStatusSubjectCates();
                    request.setAttribute("subjectCateList", subjectCateList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                }else if (field.equalsIgnoreCase("testType")) {// if admin only want to get all test type list
                    ArrayList<TestType> testTypeList = testTypeDAO.getAllStatusTestTypes();
                    request.setAttribute("testTypeList", testTypeList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                }else if (field.equalsIgnoreCase("quizLevel")) {// if admin only want to get all quiz level list
                    ArrayList<QuizLevel> quizLevelList = quizLevelDAO.getAllStatusQuizLevel();
                    request.setAttribute("quizLevelList", quizLevelList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                }else if (field.equalsIgnoreCase("lessonType")) {// if admin only want to get all lesson type list
                    ArrayList<LessonType> lessonTypeList = lessonTypeDAO.getAllStatusLessonType();
                    request.setAttribute("lessonTypeList", lessonTypeList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                }else if (field.equalsIgnoreCase("dimensionType")) {// if admin only want to get all dimension type list
                    ArrayList<DimensionType> dimensionTypeList = dimensionTypeDAO.getAllStatusDimensionTypes();
                    request.setAttribute("dimensionTypeList", dimensionTypeList);
                    request.getRequestDispatcher("jsp/settingList.jsp").forward(request, response);
                } 
            }
        } catch (Exception ex) {
            Logger.getLogger(SystemSettingController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMess", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
