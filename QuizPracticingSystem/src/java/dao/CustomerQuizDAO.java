/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  CustomerQuizDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 */
package dao;

import bean.CustomerQuiz;
import bean.QuizQuizHandle;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của CustomerQuizDAOImpl
 *
 * @author NamDH
 */
public interface CustomerQuizDAO {

    public ArrayList<CustomerQuiz> getAllCustomerQuiz() throws Exception;

    public ArrayList<CustomerQuiz> getQuizByUser(int userId) throws Exception;

    /**
     * get user's taken quiz
     *
     * @param quizTakeId target quiz. It is a <code>int</code> primitive type
     * @return a quiz. It is a <code>CustomerQuiz</code> object
     * @throws Exception
     */
    public CustomerQuiz getQuizByTakeQuizId(int quizTakeId) throws Exception;

    public int editCustomerQuiz(int customerQuizId, CustomerQuiz customerQuiz) throws Exception;

    /**
     * insert into CustomerQuiz table the quiz that just have taken by user
     *
     * @param customerQuiz the new CustomerQuiz. It is a
     * <code>CustomerQuiz</code> object
     * @return number of changes in database. It is a <code>int</code> primitive
     * type.
     * @throws java.lang.Exception
     */
    public int addCustomerQuiz(CustomerQuiz customerQuiz) throws Exception;

    /**
     * add user's answer of the taken quiz into database
     *
     * @param quiz the id of quiz that user have just taken. It is a
     * <code>QuizQuizHandle</code> object
     * @return number of changes in database <code>int</code> primitive type.
     * @throws java.lang.Exception
     */
    public int addTakeAnswer(QuizQuizHandle quiz) throws Exception;

    public int deleteCustomerQuiz(int customerQuizId) throws Exception;

    /**
     * get the last added customer quiz
     *
     * @return a customer quiz. It is a <code>CustomerQuiz</code> object.
     * @throws java.lang.Exception
     */
    public CustomerQuiz getLastAddedCustomerQuiz() throws Exception;

    public boolean checkTeakedQuiz(int quizId) throws Exception;
}
