/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.UserRole;
import dao.MyDAO;
import java.util.ArrayList;
import dao.UserRoleDAO;

/**
 *
 * @author admin
 */
public class UserRoleDAOImpl extends MyDAO implements UserRoleDAO {

    @Override
    public ArrayList<UserRole> getAllUserRole() throws Exception {
        xSql = "SELECT [userRoleId],[userRoleName],[status] FROM [QuizSystem].[dbo].[UserRole]";
        ArrayList<UserRole> allUserRole = new ArrayList<>();
        UserRole add = null;
        ps = conn.prepareStatement(xSql);
        rs = ps.executeQuery();
        while (rs.next()) {
            add = new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status"));
            allUserRole.add(add);
        }
        return allUserRole;
    }

    @Override
    public UserRole getUserRoleById(int roleId) throws Exception {
        xSql = "SELECT [userRoleId],[userRoleName],[status] FROM [QuizSystem].[dbo].[UserRole] WHERE userRoleId = " + roleId;
        UserRole userRole = null;
        ps = conn.prepareStatement(xSql);
        rs = ps.executeQuery();
        if (rs.next()) {
            userRole = new UserRole(rs.getInt("userRoleId"), rs.getString("userRoleName"), rs.getBoolean("status"));
        }
        return userRole;
    }

    @Override
    public int editRole(UserRole userRole) throws Exception {
        xSql = "UPDATE [UserRole] SET userRoleName = ?,[status] = ? WHERE userRoleId = ?";
        int i = 0;
        ps = conn.prepareStatement(xSql);
        ps.setString(1, userRole.getUserRoleName());
        ps.setBoolean(2, userRole.isStatus());
        ps.setInt(3, userRole.getUserRoleId());
        i = ps.executeUpdate();
        return i;
    }

    @Override
    public int addRole(UserRole userRole) throws Exception {
        xSql = "INSERT INTO [UserRole](userRoleName,status) VALUES(?,?)";
        int i = 0;
        ps = conn.prepareStatement(xSql);
        ps.setString(1, userRole.getUserRoleName());
        ps.setBoolean(2, userRole.isStatus());
        i = ps.executeUpdate();
        return i;
    }

    @Override
    public int deleteRole(int roleId) throws Exception {
        xSql = "DELETE FROM [UserRole] WHERE userRoleID = " + roleId;
        int i = 0;
            ps = conn.prepareStatement(xSql);
            i = ps.executeUpdate();
        
        return i;
    }

}
