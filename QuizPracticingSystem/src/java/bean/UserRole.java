/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author admin
 */
public class UserRole {
    private int userRoleId;
    private String userRoleName;
    private boolean status;

    public UserRole() {
    }

    public UserRole(int userRoleId, String userRoleName, boolean status) {
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
        this.status = status;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
