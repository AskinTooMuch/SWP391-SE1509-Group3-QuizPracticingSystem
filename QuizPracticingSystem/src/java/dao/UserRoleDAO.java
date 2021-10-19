/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserRoleDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.UserRole;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của UserRoleDAOImpl
 *
 * @author NamDH
 */
public interface UserRoleDAO {
    /**
     * Get all user role
     * @return
     * @throws Exception 
     */
    public ArrayList<UserRole> getAllUserRole() throws Exception;
    
    /**
     * Get user role with a specified id
     * @param roleId
     * @return
     * @throws Exception 
     */
    public UserRole getUserRoleById(int roleId) throws Exception;
    
    /**
     * Edit user Role
     * @param userRole
     * @return
     * @throws Exception 
     */
    public int editRole(UserRole userRole) throws Exception;
    
    /**
     * Add new user role
     * @param userRole
     * @return
     * @throws Exception 
     */
    public int addRole(UserRole userRole) throws Exception;
    
    /**
     * Delete user Role
     * @param roleId
     * @return
     * @throws Exception 
     */
    public int deleteRole(int roleId) throws Exception;
}
