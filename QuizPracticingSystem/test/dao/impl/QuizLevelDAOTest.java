/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.QuizLevel;
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
public class QuizLevelDAOTest {
    
    public QuizLevelDAOTest() {
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
     * Test of getAllQuizLevel method, of class QuizLevelDAO.
     */
    @Test
    public void testGetAllQuizLevel() {
        System.out.println("getAllQuizLevel");
        QuizLevelDAO instance = new QuizLevelDAO();
        ArrayList<QuizLevel> expResult = null;
        ArrayList<QuizLevel> result = instance.getAllQuizLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuizLevelById method, of class QuizLevelDAO.
     */
    @Test
    public void testGetQuizLevelById() {
        System.out.println("getQuizLevelById");
        int quizLevelId = 1;
        QuizLevelDAO instance = new QuizLevelDAO();
        QuizLevel expResult = null;
        QuizLevel result = instance.getQuizLevelById(quizLevelId);
        assertEquals("Hard", result.getQuizLevelName());
      
    }

    /**
     * Test of editQuizLevel method, of class QuizLevelDAO.
     */
    @Test
    public void testEditQuizLevel() {
        System.out.println("editQuizLevel");
        int quizLevelId = 0;
        QuizLevel quizLevel = null;
        QuizLevelDAO instance = new QuizLevelDAO();
        int expResult = 0;
        int result = instance.editQuizLevel(quizLevelId, quizLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addQuizLevel method, of class QuizLevelDAO.
     */
    @Test
    public void testAddQuizLevel() {
        System.out.println("addQuizLevel");
        QuizLevel quizLevel = null;
        QuizLevelDAO instance = new QuizLevelDAO();
        int expResult = 0;
        int result = instance.addQuizLevel(quizLevel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteQuizLevel method, of class QuizLevelDAO.
     */
    @Test
    public void testDeleteQuizLevel() {
        System.out.println("deleteQuizLevel");
        int quizLevelId = 0;
        QuizLevelDAO instance = new QuizLevelDAO();
        int expResult = 0;
        int result = instance.deleteQuizLevel(quizLevelId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
