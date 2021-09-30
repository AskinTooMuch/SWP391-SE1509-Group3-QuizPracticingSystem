/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Slider;
import bean.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tuan
 */
public class SliderDAOTest {
    
    public SliderDAOTest() {
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
     * Test of getSlider method, of class SliderDAO.
     */
    @Test
    public void testGetSlider() {
        System.out.println("getSlider");
        SliderDAO instance = new SliderDAO();
        int expResult = 0;
        int result = instance.getSlider().size();
        assertTrue(expResult < result);
     
    }

    /**
     * Test of getSliderById method, of class SliderDAO.
     */
    @Test
    public void testGetSliderById() {
        System.out.println("getSliderById");
        int sliderId = 1;
        SliderDAO instance = new SliderDAO();
        String expResult = "OOP with Java";
        String result = instance.getSliderById(sliderId).getSliderTitle();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

     /**
     * Test of addSlider method, of class SliderDAO.
     */
    @Test
    public void testAddSlider() {
        System.out.println("addSlider");
        Slider newSlder = new Slider(0, "testSlider", "testpic", "haha", "iidesune", true);
        SliderDAO instance = new SliderDAO();
        int expResult = 1;
        int result = instance.addSlider(newSlder);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of editSlider method, of class SliderDAO.
     */
    @Test
    public void testEditSlider() {
        System.out.println("editSlider");
        int sliderId = 0;
        Slider editedSlider = null;
        SliderDAO instance = new SliderDAO();
        int expResult = 0;
        int result = instance.editSlider(sliderId, editedSlider);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    /**
     * Test of deleteSlider method, of class SliderDAO.
     */
    @Test
    public void testDeleteSlider() {
        System.out.println("deleteSlider");
        int sliderId = 0;
        SliderDAO instance = new SliderDAO();
        int expResult = 0;
        int result = instance.deleteSlider(sliderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
