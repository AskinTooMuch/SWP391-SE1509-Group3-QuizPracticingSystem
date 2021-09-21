/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Registration;
import dao.impl.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class RegistrationDAO extends MyDAO {

    public ArrayList<Registration> getRegistration(String email, String validFrom, String validTo, boolean status, int sort) {
        return null;
    }

    public Registration getRegistrationById(int registrationId) {
        return null;
    }

    public int addRegistration(Registration newRegistration) {
        return 0;
    }

    public int editRegistration(int registrationId,Registration editedRegistration) {
        return 0;
    }
    public int deleteRegistration(int registrationId){
        return 0;
    }
}
