/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Dimension;
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
public class DimensionDAOTest {
    
    public DimensionDAOTest() {
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
     * Test of getAllDimension method, of class DimensionDAO.
     */
    @Test
    public void testGetAllDimension() {
        System.out.println("getAllDimension");
        DimensionDAO instance = new DimensionDAO();
        ArrayList<Dimension> expResult = null;
        ArrayList<Dimension> result = instance.getAllDimension();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDimensionBySubject method, of class DimensionDAO.
     */
    @Test
    public void testGetDimensionBySubject() {
        System.out.println("getDimensionBySubject");
        int subjectId = 1;
        DimensionDAO instance = new DimensionDAO();
        int expResult = 0;
        int result = instance.getDimensionBySubject(subjectId).size();
        assertTrue(expResult < result);
    }

    /**
     * Test of getDimensionById method, of class DimensionDAO.
     */
    @Test
    public void testGetDimensionById() {
        System.out.println("getDimensionById");
        DimensionDAO instance = new DimensionDAO();
        Dimension expResult = null;
        Dimension result = instance.getDimensionById();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDimension method, of class DimensionDAO.
     */
    @Test
    public void testAddDimension() {
        System.out.println("addDimension");
        Dimension dimension = null;
        DimensionDAO instance = new DimensionDAO();
        int expResult = 0;
        int result = instance.addDimension(dimension);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDimension method, of class DimensionDAO.
     */
    @Test
    public void testDeleteDimension() {
        System.out.println("deleteDimension");
        int dimensionId = 0;
        DimensionDAO instance = new DimensionDAO();
        int expResult = 0;
        int result = instance.deleteDimension(dimensionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editDimension method, of class DimensionDAO.
     */
    @Test
    public void testEditDimension() {
        System.out.println("editDimension");
        int dimensionId = 0;
        Dimension dimension = null;
        DimensionDAO instance = new DimensionDAO();
        int expResult = 0;
        int result = instance.editDimension(dimensionId, dimension);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
