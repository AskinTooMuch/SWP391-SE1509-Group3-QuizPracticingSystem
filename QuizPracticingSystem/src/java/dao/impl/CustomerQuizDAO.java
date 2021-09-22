/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.CustomerQuiz;
import dao.CustomerQuizINT;
import dao.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author ChucNVHE150618
 */
public class CustomerQuizDAO extends MyDAO implements CustomerQuizINT{
    @Override
    public ArrayList<CustomerQuiz> getAllCustomerQuiz(){
        ArrayList<CustomerQuiz> allCustomerQuiz = null;
        
        return allCustomerQuiz;
    }
    
    @Override
    public ArrayList<CustomerQuiz> getQuizByUser(int userId){
        ArrayList<CustomerQuiz> customerQuiz = null;
        
        return customerQuiz;
    }
    
    @Override
    public CustomerQuiz getQuizById(int quizId){
        CustomerQuiz customerQuiz = null;
        
        return customerQuiz;
    }
    
    @Override
    public int editCustomerQuiz(int customerQuizId, CustomerQuiz customerQuiz){
        int i = 0;
        
        return i;
    }
    
    @Override
    public int addCustomerQuiz(CustomerQuiz customerQuiz){
        int i = 0;
        
        return i;
    }
    
    @Override
    public int deleteCustomerQuiz(int customerQuizId){
        int i = 0;
        
        return i;
    }
}
