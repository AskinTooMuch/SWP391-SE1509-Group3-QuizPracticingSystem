/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 */
package dao;

import bean.User;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của UserDAOImpl
 *
 * @author NamDH
 */
public interface UserDAO {

    public ArrayList<User> getUserAllUser() throws Exception;

    /**
     * get new users
     *
     * @return <code>ArrayList<Use>r</code> object.
     * @throws java.lang.Exception
     */
    public ArrayList<User> get10NewUser() throws Exception;

    /**
     * get user from User table Using mail and password
     *
     * @param userMail is an String
     * @param password is an String
     * @return <code>User</code> object.
     * @throws java.lang.Exception
     */
    public User getUserLogin(String userMail, String password) throws Exception;

    /**
     * get user from User table using userId
     *
     * @param userId is an <code>int</code>
     * @return <code>User</code> object.
     * @throws java.lang.Exception
     */
    public User getUserById(int userId) throws Exception;

    /**
     * get user from User table using userMail
     *
     * @param userMail is an String
     * @return <code>User</code> object.
     */
    public User getUserByMail(String userMail) throws Exception;

    /**
     * get user from User table using userMobile
     *
     * @param userMobile is an String
     * @return <code>User</code> object.
     */
    public User getUserByMobile(String Moblie) throws Exception;

    /**
     * update a user from User table
     *
     * @param updatedUser is a <code>User</code> object
     * @return a int.
     */
    public int updateUser(User updatedUser) throws Exception;

    /**
     * change a user status from User table
     *
     * @param userId is an int
     * @param newStatus is a boolean object
     * @return a int.
     */
    public int changeStatus(int userId, boolean newStatus) throws Exception;

    /**
     * add a user to User table
     *
     * @param newUser is an <code>User</code> object
     * @return a int.
     */
    public int addUser(User newUser) throws Exception;

    /**
     * delete a user from User table
     *
     * @param user is an <code>User</code> object
     * @return a int.
     */
    public int deleteUser(User user) throws Exception;
}
