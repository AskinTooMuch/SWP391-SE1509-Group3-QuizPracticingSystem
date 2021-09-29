/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.TestType;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class TestTypeDAOTest {
    
    public TestTypeDAOTest() {
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
     * Test of getAllTestTypes method, of class TestTypeDAO.
     */
    @Test
    public void testGetAllTestTypes() {
        System.out.println("getAllTestTypes");
        TestTypeDAO instance = new TestTypeDAO();
        ArrayList<TestType> expResult = null;
        ArrayList<TestType> result = instance.getAllTestTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestTypeById method, of class TestTypeDAO.
     */
    @Test
    public void testGetTestTypeById() {
        System.out.println("getTestTypeById");
        int testTypeId = 1;
        TestTypeDAO instance = new TestTypeDAO();
        int expResult = 1;
        TestType result = instance.getTestTypeById(testTypeId);
        assertTrue(expResult == result.getTestTypeId());
    }

    /**
     * Test of updateTestType method, of class TestTypeDAO.
     */
    @Test
    public void testUpdateTestType() {
        System.out.println("updateTestType");
        TestType updatedTestType = null;
        TestTypeDAO instance = new TestTypeDAO();
        int expResult = 0;
        int result = instance.updateTestType(updatedTestType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTestType method, of class TestTypeDAO.
     */
    @Test
    public void testDeleteTestType() {
        System.out.println("deleteTestType");
        int testTypeId = 0;
        TestTypeDAO instance = new TestTypeDAO();
        int expResult = 0;
        int result = instance.deleteTestType(testTypeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTestType method, of class TestTypeDAO.
     */
    @Test
    public void testAddTestType() {
        System.out.println("addTestType");
        TestType newTestType = null;
        TestTypeDAO instance = new TestTypeDAO();
        int expResult = 0;
        int result = instance.addTestType(newTestType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
