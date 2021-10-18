/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserRoleDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
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
