/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Subject entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         ChucNVHE150618  First Deploy
 *  16/10/21    1.0         ChucNVHE150618  Comment
*/
package bean;

/**
 * Hold the data of userRole entity
 * @author admin
 */
public class UserRole {
    private int userRoleId; /*User role id*/
    private String userRoleName; /*User role Name*/
    private boolean status; /*UserRole status*/

    /**
     * Blank constructor
     */
    public UserRole() {
    }

    /**
     * Complete constructor
     * @param userRoleId
     * @param userRoleName
     * @param status 
     */
    public UserRole(int userRoleId, String userRoleName, boolean status) {
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
        this.status = status;
    }

    /**
     * Get userRoleID
     * @return 
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * setUserRoleID
     * @param userRoleId 
     */
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * Get userRoleName
     * @return 
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * Set userRoleName
     * @param userRoleName 
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    /**
     * Get userRole Status
     * @return 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set userRole status
     * @param status 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
