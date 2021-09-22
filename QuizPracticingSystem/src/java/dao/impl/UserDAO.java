/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.User;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserDAO extends MyDAO {

    public ArrayList<User> getUserAllUser() {
        return null;
    }

    public User getUserLogin(String userMail, String password) {
        String sql = "SELECT * FROM [User] WHERE userMail = ? and password = ? and status = 1";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userMail);
            ps.setString(2, password);
            rs = ps.executeQuery();
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

    public User getUserById(int userId) {
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

    public User getUserByMail(String userMail) {
        xSql = "SELECT * FROM [User] WHERE userMail = ?";
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, userMail);
            rs = ps.executeQuery();
            while (rs.next()) {
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

    public User getUserByMobile(String Moblie) {
        xSql = "SELECT * FROM [User] WHERE userMobile = ?";
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, Moblie);
            rs = ps.executeQuery();
            while (rs.next()) {
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

    public User getUser(String userMail, String password) {
        return null;
    }

    public int updateUser(User updatedUser) {
        xSql = " UPDATE [User] set userName = ?, [password] = ?,  roleId = ?, profilePic = ?, userMail = ?, gender = ?, userMobile = ?, status = ? where userId = ?";
        int check = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, updatedUser.getUserName());
            ps.setString(2, updatedUser.getPassword());
            ps.setInt(3, updatedUser.getRoleId());
            ps.setString(4, updatedUser.getProfilePic());
            ps.setString(5, updatedUser.getUserMail());
            ps.setBoolean(6, updatedUser.isGender());
            ps.setString(7, updatedUser.getUserMobile());
            ps.setBoolean(8, updatedUser.isStatus());
            ps.setInt(9, updatedUser.getUserId());
            check = ps.executeUpdate();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int changeStatus(int userId, boolean newStatus) {
        xSql = "UPDATE [User] set [status] = ? where userId = ?";
        int check = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, userId);
            check = ps.executeUpdate();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int addUser(User newUser) {
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
        User newUser = new User(0, "Duong", "12", 1, null, "duonghoang8801@gmail.com", true, "0852274855", true);
        ud.addUser(newUser);
//        User all = ud.getUserByMail("DuongNHHE150328@fpt.edu.vn");
//        System.out.println(all.getUserName());
    }

}
