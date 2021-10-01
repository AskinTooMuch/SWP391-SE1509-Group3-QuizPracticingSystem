/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Quiz;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ADMN
 */
public class QuizDAOTest {
    
    public QuizDAOTest() {
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
     * Test of getAllQuiz method, of class QuizDAOImpl.
     */
    @Test
    public void testGetAllQuiz() {
        System.out.println("getAllQuiz");
        QuizDAOImpl instance = new QuizDAOImpl();
        ArrayList<Quiz> expResult = null;
        ArrayList<Quiz> result = instance.getAllQuiz();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizById method, of class QuizDAOImpl.
     */
    @Test
    public void testGetQuizById() {
        System.out.println("getQuizById");
        int quizId = 1;
        QuizDAOImpl instance = new QuizDAOImpl();
        Quiz expResult = null;
        Quiz result = instance.getQuizById(quizId);
        assertEquals(2, result.getTestTypeId());

    }

    /**
     * Test of getQuizByQuizTakeId method, of class QuizDAOImpl.
     */
    @Test
    public void testGetQuizByQuizTakeId() {
        System.out.println("getQuizByQuizTakeId");
        int quizTakeId = 1;
        QuizDAOImpl instance = new QuizDAOImpl();
        Quiz expResult = null;
        Quiz result = instance.getQuizByQuizTakeId(quizTakeId);
        assertEquals(30, result.getQuizDuration());       
    }

    /**
     * Test of getQuizBySubject method, of class QuizDAOImpl.
     */
    @Test
    public void testGetQuizBySubject() {
        System.out.println("getQuizBySubject");
        int subjectId = 0;
        QuizDAOImpl instance = new QuizDAOImpl();
        ArrayList<Quiz> expResult = null;
        ArrayList<Quiz> result = instance.getQuizBySubject(subjectId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizByLesson method, of class QuizDAOImpl.
     */
    @Test
    public void testGetQuizByLesson() {
        System.out.println("getQuizByLesson");
        int lessonId = 0;
        QuizDAOImpl instance = new QuizDAOImpl();
        ArrayList<Quiz> expResult = null;
        ArrayList<Quiz> result = instance.getQuizByLesson(lessonId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editQuiz method, of class QuizDAOImpl.
     */
    @Test
    public void testEditQuiz() {
        System.out.println("editQuiz");
        int quizId = 0;
        Quiz quiz = null;
        QuizDAOImpl instance = new QuizDAOImpl();
        int expResult = 0;
        int result = instance.editQuiz(quizId, quiz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addQuiz method, of class QuizDAOImpl.
     */
    @Test
    public void testAddQuiz() {
        System.out.println("addQuiz");
        Quiz quiz = null;
        QuizDAOImpl instance = new QuizDAOImpl();
        int expResult = 0;
        int result = instance.addQuiz(quiz);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteQuiz method, of class QuizDAOImpl.
     */
    @Test
    public void testDeleteQuiz() {
        System.out.println("deleteQuiz");
        int quizId = 0;
        QuizDAOImpl instance = new QuizDAOImpl();
        int expResult = 0;
        int result = instance.deleteQuiz(quizId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class QuizDAO.
     */
  
    
}
