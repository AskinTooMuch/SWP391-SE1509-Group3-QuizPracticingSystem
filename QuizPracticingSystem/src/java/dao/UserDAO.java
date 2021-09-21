/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.User;
import dao.impl.DBConnection;
import dao.impl.MyDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public User getUserLogin(String userMail, String password) {
        String sql = "SELECT * FROM [User] WHERE userMail = ? and password = ? and status = 1";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ps.setString(1, userMail);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getInt("roleId"),
                            rs.getString("profilePic"),
                            rs.getString("userMail"),
                            rs.getBoolean("gender"),
                            rs.getString("userMobile"),
                            rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public boolean isExistedEmail(String userMail){
        xSql = "SELECT * FROM [User] WHERE userMail = ?";
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, userMail);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
        xSql = "INSERT INTO [User](userName,[password],roleId,userMail,gender,userMobile,[status])"
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
    
    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        User newUser = new User(0, "Duong", "12", 1, null, "duonghoang88@gmail.com", true, "0852274855", true);
        ud.addUser(newUser);
    }
        
}
