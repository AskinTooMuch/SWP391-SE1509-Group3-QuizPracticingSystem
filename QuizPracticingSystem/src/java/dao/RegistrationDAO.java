/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.*;
import dao.impl.RegistrationDAOImpl;
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

    public ArrayList<SubjectDashboard> View(String from,String to, ArrayList<Subject> subjectList, String type) throws Exception;
    
    public ArrayList<String> convertJson(ArrayList<SubjectDashboard> viewList ) throws Exception;
    
    public ArrayList<Subject> getRegistedSubject(int userId) throws Exception;
}
