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

    public ArrayList<CustomerQuiz> getAllCustomerQuiz();

    public ArrayList<CustomerQuiz> getQuizByUser(int userId);

    public CustomerQuiz getQuizById(int quizId);

    public int editCustomerQuiz(int customerQuizId, CustomerQuiz customerQuiz);

    public int addCustomerQuiz(CustomerQuiz customerQuiz);

    public int addTakeAnswer(QuizQuizHandle quiz);

    public int deleteCustomerQuiz(int customerQuizId);
    
    public int addMarkQuestion(QuizQuizHandle quiz);
}