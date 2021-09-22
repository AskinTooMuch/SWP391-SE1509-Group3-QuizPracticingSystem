/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CustomerQuiz;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface CustomerQuizINT {
    public ArrayList<CustomerQuiz> getAllCustomerQuiz();
    
    public ArrayList<CustomerQuiz> getQuizByUser(int userId);
    
    public CustomerQuiz getQuizById(int quizId);
    
    public int editCustomerQuiz(int customerQuizId, CustomerQuiz customerQuiz);
    
    public int addCustomerQuiz(CustomerQuiz customerQuiz);
    
    public int deleteCustomerQuiz(int customerQuizId);
}
