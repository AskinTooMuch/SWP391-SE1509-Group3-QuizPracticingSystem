/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Registration;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class RegistrationDAO extends MyDAO {

    public ArrayList<Registration> getRegistration(String email, String validFrom, String validTo, boolean status, int sort) {
        return null;
    }

    public Registration getRegistrationById(int id) {
        return null;
    }

    public int addRegistration(Registration a) {
        return 0;
    }

    public int editRegistration(Registration a) {
        return 0;
    }
}
