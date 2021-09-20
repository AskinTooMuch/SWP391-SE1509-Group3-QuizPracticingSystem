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
public class UserDAO {

    public ArrayList<User> getUser(int userId, int roleId, boolean gender, boolean status, String userName, String password, String profilePic, String userMail, String userMobile) {
        return null;
    }
    public User getUser(String userName, String password) {
        return null;
    }
    public int updateUser(User updatedUser){
        return 0;
    }
    public int changeStatus(boolean status){
        return 0;}
}
