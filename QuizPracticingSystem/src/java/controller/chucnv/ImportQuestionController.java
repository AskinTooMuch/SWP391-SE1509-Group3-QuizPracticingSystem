/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 21, 2021
 *  Question Import servlet
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  21/10/21    1.0         ChucNVHE150618  First Deploy
 */
package controller.chucnv;

import bean.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
/**
 *
 * @author admin
 */
public class ImportQuestionController extends HttpServlet {

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
            String service = request.getParameter("service");

            /*Service is null, redirect user to index*/
            if (service == null) {
                sendDispatcher(request, response, "index.jsp");
            }

            if ("uploadQuestion".equalsIgnoreCase(service)) {
                String uploadContent = (String) request.getParameter("uploadQuestions");
                ArrayList<Question> questionList = new ArrayList<>();
                
                String[] uploadContentPart = uploadContent.split("<qps>");
                /**
                 * Extract question content from the array of strings
                 */
                for (int i = 3; i < uploadContentPart.length; i++) {
                    
                }
                
                request.setAttribute("uploadContentPart", uploadContentPart);
                sendDispatcher(request, response, "jsp/questionImport.jsp");
            }
        }
    }

/**
 * Forward the request to the destination, catch any unexpected exceptions and
 * log it
 *
 * @param request Request of the servlet
 * @param response Response of the servlet
 * @param path Forward address
 */
public void sendDispatcher(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);

        

} catch (ServletException | IOException ex) {
            Logger.getLogger(ImportQuestionController.class

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
