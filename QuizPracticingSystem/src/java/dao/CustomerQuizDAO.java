/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CustomerQuiz;
import bean.QuizQuizHandle;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface CustomerQuizDAO {

    public ArrayList<CustomerQuiz> getAllCustomerQuiz() throws Exception;

    public ArrayList<CustomerQuiz> getQuizByUser(int userId) throws Exception;

    public CustomerQuiz getQuizByTakeQuizId(int quizTakeId) throws Exception;

    public int editCustomerQuiz(int customerQuizId, CustomerQuiz customerQuiz) throws Exception;

    public int addCustomerQuiz(CustomerQuiz customerQuiz) throws Exception;

    public int addTakeAnswer(QuizQuizHandle quiz) throws Exception;

    public int deleteCustomerQuiz(int customerQuizId) throws Exception;
    
//    public int addMarkQuestion(QuizQuizHandle quiz) throws Exception;
    
    public CustomerQuiz getLastAddedCustomerQuiz() throws Exception;
}
