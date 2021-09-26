/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.UserRole;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface UserRoleINT {
    public ArrayList<UserRole> getAllUserRole();
    
    public UserRole getUserRoleById(int roleId);
    
    public int editRole(int roleId, UserRole userRole);
    
    public int addRole(UserRole userRole);
    
    public int deleteRole(int roleId);
}
