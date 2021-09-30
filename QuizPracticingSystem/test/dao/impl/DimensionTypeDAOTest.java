/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.DimensionType;
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
public class DimensionTypeDAOTest {
    
    public DimensionTypeDAOTest() {
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
     * Test of getAllDimensionTypes method, of class DimensionTypeDAO.
     */
    @Test
    public void testGetAllDimensionTypes() {
        System.out.println("getAllDimensionTypes");
        DimensionTypeDAO instance = new DimensionTypeDAO();
        ArrayList<DimensionType> expResult = null;
        ArrayList<DimensionType> result = instance.getAllDimensionTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDimensionTypeById method, of class DimensionTypeDAO.
     */
    @Test
    public void testGetDimensionTypeById1() {
        System.out.println("getDimensionTypeById");
        int dimensionTypeId = 1;
        DimensionTypeDAO instance = new DimensionTypeDAO();
        DimensionType expResult = new DimensionType(1, "Domain", true);
        DimensionType result = instance.getDimensionTypeById(dimensionTypeId);
        assertEquals(expResult.getDimensionTypeId(), result.getDimensionTypeId());
    }

    /**
     * Test of updateDimensionType method, of class DimensionTypeDAO.
     */
    @Test
    public void testUpdateDimensionType() {
        System.out.println("updateDimensionType");
        DimensionType updatedDimensionType = null;
        DimensionTypeDAO instance = new DimensionTypeDAO();
        int expResult = 0;
        int result = instance.updateDimensionType(updatedDimensionType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deteteDimensionTyoe method, of class DimensionTypeDAO.
     */
    @Test
    public void testDeteteDimensionTyoe() {
        System.out.println("deteteDimensionTyoe");
        int dtId = 0;
        DimensionTypeDAO instance = new DimensionTypeDAO();
        int expResult = 0;
        int result = instance.deteteDimensionTyoe(dtId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDimensionType method, of class DimensionTypeDAO.
     */
    @Test
    public void testAddDimensionType() {
        System.out.println("addDimensionType");
        DimensionType newDimensionType = null;
        DimensionTypeDAO instance = new DimensionTypeDAO();
        int expResult = 0;
        int result = instance.addDimensionType(newDimensionType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
