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
public interface UserINT {
    
    public ArrayList<User> getUserAllUser();
    
    public User getUserLogin(String userMail, String password);
    
    public User getUserById(int userId);
    
    public User getUserByMail(String userMail);
    
    public User getUserByMobile(String Moblie);
    
    public User getUser(String userMail, String password);
    
    public int updateUser(User updatedUser);
    
    public int changeStatus(int userId, boolean newStatus);
    
    public int addUser(User newUser);
}
