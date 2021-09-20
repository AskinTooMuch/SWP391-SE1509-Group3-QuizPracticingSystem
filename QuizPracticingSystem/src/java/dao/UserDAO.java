/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.User;
import dao.impl.MyDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserDAO extends MyDAO{

    public ArrayList<User> getUserAllUser() {
        return null;
    }
    
    public User getUserById(int userId){
        xSql = "SELECT * FROM [User] WHERE userId = ?";
        User user = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getInt("roleId"),
                            rs.getString("profilePic"),
                            rs.getString("userMail"),
                            rs.getBoolean("gender"),
                            rs.getString("userMobile"),
                            rs.getBoolean("status"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User getUser(String userMail, String password) {
        return null;
    }
    
    public int updateUser(User updatedUser){
        return 0;
    }
    
    public int changeStatus(boolean status){
        return 0;
    }
    
    public int addUser(User newUser){
        xSql = "INSERT INTO [User](userName,password,roleId,userMail,gender,userMobile,status)"
                        + "values(?,?,?,?,?,?,?)";
        int check = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, newUser.getUserName());
            ps.setString(2, newUser.getPassword());
            ps.setInt(3, 1);
            ps.setString(4, newUser.getUserMail());
            ps.setBoolean(5, newUser.isGender());
            ps.setString(6, newUser.getUserMobile());
            ps.setBoolean(7, false);
            check = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
}
