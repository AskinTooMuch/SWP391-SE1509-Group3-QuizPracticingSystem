/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.User;
import dao.impl.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class UserDAO extends MyDAO{

    public ArrayList<User> getUserAllUser() {
        return null;
    }
    
    public User getUser(String userName, String password) {
        return null;
    }
    
    public int updateUser(User updatedUser){
        return 0;
    }
    
    public int changeStatus(boolean status){
        return 0;
    }
    
    public int addUser(User newUser){
        return 0;
    }
}
