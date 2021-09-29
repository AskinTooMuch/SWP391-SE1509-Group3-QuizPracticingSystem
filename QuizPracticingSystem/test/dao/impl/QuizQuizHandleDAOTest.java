/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Question;
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
 * @author ADMN
 */
public class QuizQuizHandleDAOTest {
    
    public QuizQuizHandleDAOTest() {
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
     * Test of generateQuiz method, of class QuizQuizHandleDAO.
     */
    @Test
    public void testGenerateQuiz() {
        System.out.println("generateQuiz");
        QuestionDAO questionDAO = new QuestionDAO();
        ArrayList<Question> questionList = questionDAO.getQuestionByQuizId(1);
        int quizId = 0;
        QuizQuizHandleDAO instance = new QuizQuizHandleDAO();
        QuizQuizHandle expResult = null;
        QuizQuizHandle result = instance.generateQuiz(questionList, 1);
        assertEquals(30, result.getTime());
    }

    /**
     * Test of calculateScore method, of class QuizQuizHandleDAO.
     */
    @Test
    public void testCalculateScore() {
        System.out.println("calculateScore");
        QuizQuizHandleDAO instance = new QuizQuizHandleDAO();
        QuizQuizHandle questionList = instance.getReviewQuiz(1);
        double expResult = 0.0;
        double result = instance.calculateScore(questionList);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAnsweredQuestion method, of class QuizQuizHandleDAO.
     */
    @Test
    public void testGetAnsweredQuestion() {
        System.out.println("getAnsweredQuestion");
        QuizQuizHandle quiz = null;
        QuizQuizHandleDAO instance = new QuizQuizHandleDAO();
        QuizQuizHandle questionList = instance.getReviewQuiz(1);
        int expResult = 0;
        int result = instance.getAnsweredQuestion(questionList);
        assertEquals(0, result);
    }

    /**
     * Test of getReviewQuiz method, of class QuizQuizHandleDAO.
     */
    @Test
    public void testGetReviewQuiz() {
        System.out.println("getReviewQuiz");
        int quizTakeId = 1;
        QuizQuizHandleDAO instance = new QuizQuizHandleDAO();
        QuizQuizHandle expResult = null;
        QuizQuizHandle result = instance.getReviewQuiz(quizTakeId);
        assertEquals(10, result.getQuestions().size());
       
    }
    
}
