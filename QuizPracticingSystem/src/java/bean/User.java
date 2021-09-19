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
public class User {
    private int userId, roldId;
    private boolean gender, status;
    private String userName, password, profilePic, userMail, userMobile;

    public User() {
    }

    public User(int userId, int roldId, boolean gender, boolean status, String userName, String password, String profilePic, String userMail, String userMobile) {
        this.userId = userId;
        this.roldId = roldId;
        this.gender = gender;
        this.status = status;
        this.userName = userName;
        this.password = password;
        this.profilePic = profilePic;
        this.userMail = userMail;
        this.userMobile = userMobile;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoldId() {
        return roldId;
    }

    public void setRoldId(int roldId) {
        this.roldId = roldId;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    
    
}
