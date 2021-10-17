/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.ItemDashboard;
import bean.Registration;
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
 * @author Admin
 */
public class RegistrationDAOTest {
    
    public RegistrationDAOTest() {
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
     * Test of getAllRegistration method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetAllRegistration() throws Exception {
        System.out.println("getAllRegistration");
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<Registration> expResult = null;
        ArrayList<Registration> result = instance.getAllRegistration();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistrationById method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRegistrationById() throws Exception {
        System.out.println("getRegistrationById");
        int registrationId = 0;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        Registration expResult = null;
        Registration result = instance.getRegistrationById(registrationId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRegistration method, of class RegistrationDAOImpl.
     */
    @Test
    public void testAddRegistration() throws Exception {
        System.out.println("addRegistration");
        Registration newRegistration = null;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        int result = instance.addRegistration(newRegistration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editRegistration method, of class RegistrationDAOImpl.
     */
    @Test
    public void testEditRegistration() throws Exception {
        System.out.println("editRegistration");
        int registrationId = 0;
        Registration editedRegistration = null;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        int result = instance.editRegistration(registrationId, editedRegistration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRegistration method, of class RegistrationDAOImpl.
     */
    @Test
    public void testDeleteRegistration() throws Exception {
        System.out.println("deleteRegistration");
        int registrationId = 0;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        int result = instance.deleteRegistration(registrationId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistedSubject method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRegistedSubject() throws Exception {
        System.out.println("getRegistedSubject");
        int userId = 1;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        ArrayList<Subject> result = instance.getRegistedSubject(userId);
        assertTrue(expResult <= result.size());
    }

    /**
     * Test of getRegistedSubjectbyUserId method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRegistedSubjectbyUserId() throws Exception {
        System.out.println("getRegistedSubjectbyUserId");
        int userId = 0;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<Subject> expResult = null;
        ArrayList<Subject> result = instance.getRegistedSubjectbyUserId(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubjectStasistic method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetSubjectStasistic() throws Exception {
        System.out.println("getSubjectStasistic");
        String from = "";
        String to = "";
        ArrayList<Subject> subjectList = null;
        String type = "";
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<ItemDashboard> expResult = null;
        ArrayList<ItemDashboard> result = instance.getSubjectStasistic(from, to, subjectList, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRevenueStasistic method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRevenueStasistic() {
        System.out.println("getRevenueStasistic");
        String from = "";
        String to = "";
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<ItemDashboard> expResult = null;
        ArrayList<ItemDashboard> result = instance.getRevenueStasistic(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRevenueStasisticBySubjectCate method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRevenueStasisticBySubjectCate() throws Exception {
        System.out.println("getRevenueStasisticBySubjectCate");
        String from = "";
        String to = "";
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<ItemDashboard> expResult = null;
        ArrayList<ItemDashboard> result = instance.getRevenueStasisticBySubjectCate(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertJson method, of class RegistrationDAOImpl.
     */
    @Test
    public void testConvertJson() throws Exception {
        System.out.println("convertJson");
        ArrayList<ItemDashboard> viewList = null;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.convertJson(viewList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameList method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetNameList() throws Exception {
        System.out.println("getNameList");
        ArrayList<ItemDashboard> viewList = null;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getNameList(viewList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class RegistrationDAOImpl.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        RegistrationDAOImpl.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
