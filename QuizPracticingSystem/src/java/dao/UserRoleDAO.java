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
public interface UserRoleDAO {
    public ArrayList<UserRole> getAllUserRole() throws Exception;
    
    public UserRole getUserRoleById(int roleId) throws Exception;
    
    public int editRole(UserRole userRole) throws Exception;
    
    public int addRole(UserRole userRole) throws Exception;
    
    public int deleteRole(int roleId) throws Exception;
}
