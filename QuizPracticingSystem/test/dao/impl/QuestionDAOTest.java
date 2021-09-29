/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Question;
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
public class QuestionDAOTest {
    
    public QuestionDAOTest() {
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
     * Test of getAllQuestion method, of class QuestionDAO.
     */
    @Test
    public void testGetAllQuestion() {
        System.out.println("getAllQuestion");
        QuestionDAO instance = new QuestionDAO();
        ArrayList<Question> expResult = null;
        ArrayList<Question> result = instance.getAllQuestion();
        assertEquals(10, result.size());
        
    }

    /**
     * Test of getQuestionById method, of class QuestionDAO.
     */
    @Test
    public void testGetQuestionById() {
        System.out.println("getQuestionById");
        int questionId = 1;
        QuestionDAO instance = new QuestionDAO();
        Question expResult = null;
        Question result = instance.getQuestionById(questionId);
        assertEquals(1, result.getQuestionId());       
    }

    /**
     * Test of getQuestionByQuizId method, of class QuestionDAO.
     */
    @Test
    public void testGetQuestionByQuizId() {
        System.out.println("getQuestionByQuizId");
        int quizId = 1;
        QuestionDAO instance = new QuestionDAO();
        ArrayList<Question> expResult = null;
        ArrayList<Question> result = instance.getQuestionByQuizId(quizId);
        assertEquals(10, result.size());
       
    }

    /**
     * Test of addQuestion method, of class QuestionDAO.
     */
    @Test
    public void testAddQuestion() {
        System.out.println("addQuestion");
        Question newQuestion = new Question(1,2,2,5,"hon","","nihongo",true);
        QuestionDAO instance = new QuestionDAO();
        int expResult = 0;
        int result = instance.addQuestion(newQuestion);
        assertEquals(1, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of editQuestion method, of class QuestionDAO.
     */
    @Test
    public void testEditQuestion() {
        System.out.println("editQuestion");
        int questionId = 0;
        Question editedQuestion = null;
        QuestionDAO instance = new QuestionDAO();
        int expResult = 0;
        int result = instance.editQuestion(questionId, editedQuestion);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");

    }

    /**
     * Test of deleteQuestion method, of class QuestionDAO.
     */
    @Test
    public void testDeleteQuestion() {
        System.out.println("deleteQuestion");
        int questionId = 0;
        QuestionDAO instance = new QuestionDAO();
        int expResult = 0;
        int result = instance.deleteQuestion(questionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importQuestion method, of class QuestionDAO.
     */
    @Test
    public void testImportQuestion() {
        System.out.println("importQuestion");
        ArrayList<Question> questionList = null;
        QuestionDAO instance = new QuestionDAO();
        int expResult = 0;
        int result = instance.importQuestion(questionList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class QuestionDAO.
     */
    
}
