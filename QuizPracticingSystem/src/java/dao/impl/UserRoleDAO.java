/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.UserRole;
import dao.MyDAO;
import dao.UserRoleINT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserRoleDAO extends MyDAO implements UserRoleINT{

    @Override
    public ArrayList<UserRole> getAllUserRole() {
        xSql = "SELECT [userRoleId],[userRoleName],[status] FROM [QuizSystem].[dbo].[UserRole]";
        ArrayList<UserRole> allUserRole = new ArrayList<>();
        UserRole add = null;
        try {
            ps = conn.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()){
                add = new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status"));
                allUserRole.add(add);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUserRole;
    }

    @Override
    public UserRole getUserRoleById(int roleId) {
        xSql = "SELECT [userRoleId],[userRoleName],[status] FROM [QuizSystem].[dbo].[UserRole] WHERE userRoleId = "+roleId;
        UserRole userRole = null;
        try {
            ps = conn.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()){
                userRole = new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userRole;
    }

    @Override
    public int editRole(UserRole userRole) {
        xSql = "UPDATE [UserRole] SET userRoleName = ?,[status] = ? WHERE userRoleId = ?";
        int i = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, userRole.getUserRoleName());
            ps.setBoolean(2, userRole.isStatus());
            ps.setInt(3, userRole.getUserRoleId());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int addRole(UserRole userRole) {
        xSql = "INSERT INTO [UserRole](userRoleName,status) VALUES(?,?)";
        int i = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setString(1, userRole.getUserRoleName());
            ps.setBoolean(2, userRole.isStatus());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int deleteRole(int roleId) {
        xSql = "DELETE FROM [UserRole] WHERE userRoleID = "+roleId;
        int i = 0;
        try {
            ps = conn.prepareStatement(xSql);
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
}
