/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.CustomerQuiz;
import bean.QuizQuizHandle;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bùi Thanh Tùng
 */
public class CustomerQuizDAOTest {
    
    public CustomerQuizDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllCustomerQuiz method, of class CustomerQuizDAO.
     */
    @Test
    public void testGetAllCustomerQuiz() {
        System.out.println("getAllCustomerQuiz");
        CustomerQuizDAO instance = new CustomerQuizDAO();
        ArrayList<CustomerQuiz> expResult = null;
        ArrayList<CustomerQuiz> result = instance.getAllCustomerQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizByUser method, of class CustomerQuizDAO.
     */
    @Test
    public void testGetQuizByUser() {
        System.out.println("getQuizByUser");
        int userId = 0;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        ArrayList<CustomerQuiz> expResult = null;
        ArrayList<CustomerQuiz> result = instance.getQuizByUser(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizById method, of class CustomerQuizDAO.
     */
    @Test
    public void testGetQuizById() {
        System.out.println("getQuizById");
        int quizId = 0;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        CustomerQuiz expResult = null;
        CustomerQuiz result = instance.getQuizById(quizId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCustomerQuiz method, of class CustomerQuizDAO.
     */
    @Test
    public void testEditCustomerQuiz() {
        System.out.println("editCustomerQuiz");
        int customerQuizId = 0;
        CustomerQuiz customerQuiz = null;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        int expResult = 0;
        int result = instance.editCustomerQuiz(customerQuizId, customerQuiz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCustomerQuiz method, of class CustomerQuizDAO.
     */
    @Test
    public void testAddCustomerQuiz() {
        System.out.println("addCustomerQuiz");
        CustomerQuiz customerQuiz = null;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        int expResult = 0;
        int result = instance.addCustomerQuiz(customerQuiz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastAddedCustomerQuiz method, of class CustomerQuizDAO.
     */
    @Test
    public void testGetLastAddedCustomerQuiz() {
        System.out.println("getLastAddedCustomerQuiz");
        CustomerQuizDAO instance = new CustomerQuizDAO();
        CustomerQuiz expResult = null;
        CustomerQuiz result = instance.getLastAddedCustomerQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCustomerQuiz method, of class CustomerQuizDAO.
     */
    @Test
    public void testDeleteCustomerQuiz() {
        System.out.println("deleteCustomerQuiz");
        int customerQuizId = 0;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        int expResult = 0;
        int result = instance.deleteCustomerQuiz(customerQuizId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTakeAnswer method, of class CustomerQuizDAO.
     */
    @Test
    public void testAddTakeAnswer() {
        System.out.println("addTakeAnswer");
        QuizQuizHandle quiz = null;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        int expResult = 0;
        int result = instance.addTakeAnswer(quiz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMarkQuestion method, of class CustomerQuizDAO.
     */
    @Test
    public void testAddMarkQuestion() {
        System.out.println("addMarkQuestion");
        QuizQuizHandle quiz = null;
        CustomerQuizDAO instance = new CustomerQuizDAO();
        int expResult = 0;
        int result = instance.addMarkQuestion(quiz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CustomerQuizDAO.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CustomerQuizDAO.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
