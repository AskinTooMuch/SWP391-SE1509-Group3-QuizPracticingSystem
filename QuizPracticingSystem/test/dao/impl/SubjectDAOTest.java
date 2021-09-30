/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Subject;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class SubjectDAOTest {
    
    public SubjectDAOTest() {
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
     * Test of getAllSubjects method, of class SubjectDAO.
     */
    @Test
    public void testGetAllSubjects() {
        System.out.println("getAllSubjects");
        SubjectDAO instance = new SubjectDAO();
        int expResult = 4;
        int result = instance.getAllSubjects().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFeaturedSubjects method, of class SubjectDAO.
     */
    @Test
    public void testGetFeaturedSubjects() {
        System.out.println("getFeaturedSubjects");
        SubjectDAO instance = new SubjectDAO();
        int expResult = 3;
        int result = instance.getFeaturedSubjects().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubjectsAssigned method, of class SubjectDAO.
     */
    @Test
    public void testGetSubjectsAssigned() {
        System.out.println("getSubjectsAssigned");
        int userId = 6;
        SubjectDAO instance = new SubjectDAO();
        int expResult = 3;
        int result = instance.getSubjectsAssigned(userId).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubjectbyId method, of class SubjectDAO.
     */
    @Test
    public void testGetSubjectbyId() {
        System.out.println("getSubjectbyId");
        int subjectId = 1;
        SubjectDAO instance = new SubjectDAO();
        int expResult = 1;
        int result = instance.getSubjectbyId(subjectId).getSubjectId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubjectbyCateId method, of class SubjectDAO.
     */
    @Test
    public void testGetSubjectbyCateId() {
        System.out.println("getSubjectbyCateId");
        int cateId = 1;
        SubjectDAO instance = new SubjectDAO();
        int expResult = 1;
        ArrayList<Subject> result = instance.getSubjectbyCateId(cateId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of updateSubject method, of class SubjectDAO.
     */
    @Test
    public void testUpdateSubject() {
        System.out.println("updateSubject");
        int subjectId = 0;
        Subject subject = null;
        SubjectDAO instance = new SubjectDAO();
        int expResult = 0;
        int result = instance.updateSubject(subjectId, subject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSubject method, of class SubjectDAO.
     */
    @Test
    public void testAddSubject() {
        System.out.println("addSubject");
        Subject subject = null;
        SubjectDAO instance = new SubjectDAO();
        int expResult = 0;
        int result = instance.addSubject(subject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSubject method, of class SubjectDAO.
     */
    @Test
    public void testDeleteSubject() {
        System.out.println("deleteSubject");
        int subjectId = 0;
        SubjectDAO instance = new SubjectDAO();
        int expResult = 0;
        int result = instance.deleteSubject(subjectId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
