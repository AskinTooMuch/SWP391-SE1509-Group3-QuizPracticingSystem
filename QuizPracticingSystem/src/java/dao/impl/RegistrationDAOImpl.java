/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Registration;
import dao.DBConnection;
import dao.MyDAO;
import java.util.ArrayList;
import dao.RegistrationDAO;

/**
 *
 * @author admin
 */
public class RegistrationDAOImpl extends DBConnection implements RegistrationDAO{

    @Override
    public ArrayList<Registration> getAllRegistration() throws Exception {
        return null;
    }

    @Override
    public Registration getRegistrationById(int registrationId) throws Exception {
        return null;
    }

    @Override
    public int addRegistration(Registration newRegistration) throws Exception {
        return 0;
    }

    @Override
    public int editRegistration(int registrationId,Registration editedRegistration) throws Exception {
        return 0;
    }
        
    @Override
    public int deleteRegistration(int registrationId) throws Exception{
        return 0;
    }
}
