/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.UserRole;
import dao.impl.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class UserRoleDAO extends MyDAO{
    public ArrayList<UserRole> getAllUserRole(){
        return null;
    }
    public int changeRole(int userRoleId){
        return 0;
    }
    public int changeStatus(boolean status){
        return 0;
    }
}
