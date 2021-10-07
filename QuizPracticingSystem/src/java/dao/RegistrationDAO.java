/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface RegistrationDAO {
    
    public ArrayList<Registration> getAllRegistration() throws Exception;
    
    public Registration getRegistrationById(int registrationId) throws Exception;
    
    public int addRegistration(Registration newRegistration) throws Exception;
    
    public int editRegistration(int registrationId,Registration editedRegistration) throws Exception;
    
    public int deleteRegistration(int registrationId) throws Exception;
    
    public void convertJson(ArrayList<Subject> registrationList ) throws Exception;
            
    public ArrayList<Subject> getRegistedSubject(int userId) throws Exception;
}
