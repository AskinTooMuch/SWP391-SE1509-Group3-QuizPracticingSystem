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
        ArrayList<UserRole> allUserRole = null;
        try {
            ps = conn.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()){
                allUserRole.add(new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status")));
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
    public int editRole(int roleId, UserRole userRole) {
        xSql = "UPDATE [UserRole] SET userRoleId = ?, userRoleName = ?,[status] = ? WHERE userRoleId = ?";
        int i = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, userRole.getUserRoleId());
            ps.setString(2, userRole.getUserRoleName());
            ps.setBoolean(3, userRole.isStatus());
            i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    @Override
    public int addRole(UserRole userRole) {
        xSql = "INSERT INTO [UserRole](userRoleId,userRoleName,status) VAULES(?,?,?)";
        int i = 0;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, userRole.getUserRoleId());
            ps.setString(2, userRole.getUserRoleName());
            ps.setBoolean(3, userRole.isStatus());
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
