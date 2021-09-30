/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

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
 * @author Admin
 */
public class UserDAOTest {

    public UserDAOTest() {
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
     * Test of getUserAllUser method, of class UserDAO.
     */
    @Test
    public void testGetUserAllUser() {
        System.out.println("getUserAllUser");
        UserDAO instance = new UserDAO();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.getUserAllUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserLogin method, of class UserDAO.
     */
    @Test
    public void testGetUserLogin() {
        System.out.println("getUserLogin");
        String userMail = "DuongNHHE150328@fpt.edu.vn";
        String password = "1";
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.getUserLogin(userMail, password);
        assertTrue(result.getPassword().equalsIgnoreCase("1"));
    }

    /**
     * Test of getUserById method, of class UserDAO.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int userId = 1;
        UserDAO instance = new UserDAO();
        int expResult = 1;
        User result = instance.getUserById(userId);
        assertTrue(expResult == result.getUserId());
    }

    /**
     * Test of getUserByMail method, of class UserDAO.
     */
    @Test
    public void testGetUserByMail() {
        System.out.println("getUserByMail");
        String userMail = "DuongNHHE150328@fpt.edu.vn";
        UserDAO instance = new UserDAO();
        String expResult = "DuongNHHE150328@fpt.edu.vn";
        User result = instance.getUserByMail(userMail);
        assertTrue(expResult.equalsIgnoreCase(result.getUserMail()));
    }

    /**
     * Test of getUserByMobile method, of class UserDAO.
     */
    @Test
    public void testGetUserByMobile() {
        System.out.println("getUserByMobile");
        String Moblie = "0969044713";
        UserDAO instance = new UserDAO();
        String expResult = "0969044713";
        User result = instance.getUserByMobile(Moblie);
        assertTrue(expResult.equalsIgnoreCase(result.getUserMobile()));
    }

    /**
     * Test of updateUser method, of class UserDAO.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        UserDAO instance = new UserDAO();
        User updatedUser = instance.getUserById(1);
        int expResult = 1;
        int result = instance.updateUser(updatedUser);
        assertTrue(expResult == result);
    }

    /**
     * Test of changeStatus method, of class UserDAO.
     */
    @Test
    public void testChangeStatus() {
        System.out.println("changeStatus");
        int userId = 1;
        boolean newStatus = true;
        UserDAO instance = new UserDAO();
        int expResult = 1;
        int result = instance.changeStatus(userId, newStatus);
        assertTrue(expResult == result);
    }

    /**
     * Test of addUser method, of class UserDAO.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User newUser = new User(0, "Dong01", "1", 1, null, "duonghoang8805", true, "0842274855", true);
        UserDAO instance = new UserDAO();
        int expResult = 1;
        int result = instance.addUser(newUser);
        assertTrue(expResult == result);
    }

    /**
     * Test of deleteUser method, of class UserDAO.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        UserDAO instance = new UserDAO();
        User user = instance.getUserByMail("duonghoang8805");
        int expResult = 1;
        int result = instance.deleteUser(user);
        assertTrue(expResult == result);
    }

}
