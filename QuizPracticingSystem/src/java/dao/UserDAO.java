/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.User;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface UserDAO {
    
    public ArrayList<User> getUserAllUser() throws Exception;
    
    public User getUserLogin(String userMail, String password) throws Exception;
    
    public User getUserById(int userId) throws Exception;
    
    public User getUserByMail(String userMail) throws Exception;
    
    public User getUserByMobile(String Moblie) throws Exception;
        
    public int updateUser(User updatedUser) throws Exception;
    
    public int changeStatus(int userId, boolean newStatus) throws Exception;
    
    public int addUser(User newUser) throws Exception;
    
    public int deleteUser(User user) throws Exception;
}
