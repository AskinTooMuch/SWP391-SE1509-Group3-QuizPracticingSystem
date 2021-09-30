/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.SubjectCate;
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
public class SubjectCateDAOTest {
    
    public SubjectCateDAOTest() {
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
     * Test of getAllSubjectCates method, of class SubjectCateDAO.
     */
    @Test
    public void testGetAllSubjectCates() {
        System.out.println("getAllSubjectCates");
        SubjectCateDAO instance = new SubjectCateDAO();
        ArrayList<SubjectCate> expResult = null;
        ArrayList<SubjectCate> result = instance.getAllSubjectCates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubjectCateById method, of class SubjectCateDAO.
     */
    @Test
    public void testGetSubjectCateById() {
        System.out.println("getSubjectCateById");
        int scId = 0;
        SubjectCateDAO instance = new SubjectCateDAO();
        SubjectCate expResult = null;
        SubjectCate result = instance.getSubjectCateById(scId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubjectCateBySubject method, of class SubjectCateDAO.
     */
    @Test
    public void testGetSubjectCateBySubject1() {
        System.out.println("getSubjectCateBySubject");
        int subjectId = 1;
        SubjectCateDAO instance = new SubjectCateDAO();
        int expResult = 2;
        ArrayList<SubjectCate> result = instance.getSubjectCateBySubject(subjectId);
        assertEquals(expResult, result.size());
    }
    
    @Test
    public void testGetSubjectCateBySubject2() {
        System.out.println("getSubjectCateBySubject");
        int subjectId = 2;
        SubjectCateDAO instance = new SubjectCateDAO();
        int expResult = 1;
        ArrayList<SubjectCate> result = instance.getSubjectCateBySubject(subjectId);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of updateSubjectCate method, of class SubjectCateDAO.
     */
    @Test
    public void testUpdateSubjectCate() {
        System.out.println("updateSubjectCate");
        SubjectCate updatedSubjectCate = null;
        SubjectCateDAO instance = new SubjectCateDAO();
        int expResult = 0;
        int result = instance.updateSubjectCate(updatedSubjectCate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deteleSubjectCate method, of class SubjectCateDAO.
     */
    @Test
    public void testDeteleSubjectCate() {
        System.out.println("deteleSubjectCate");
        int scId = 0;
        SubjectCateDAO instance = new SubjectCateDAO();
        int expResult = 0;
        int result = instance.deteleSubjectCate(scId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
