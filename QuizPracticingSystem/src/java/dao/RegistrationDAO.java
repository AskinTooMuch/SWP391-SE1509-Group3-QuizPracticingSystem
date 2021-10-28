/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  RegistrationDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của RegistrationDAOImpl
 *
 * @author NamDH
 */
public interface RegistrationDAO {

    public ArrayList<Registration> getAllRegistration() throws Exception;

    public Registration getRegistrationById(int registrationId) throws Exception;

    public int addRegistration(Registration newRegistration) throws Exception;

    public int editRegistration(int registrationId, Registration editedRegistration) throws Exception;

    public int deleteRegistration(int registrationId) throws Exception;

    public ArrayList<ItemDashboard> getSubjectStatistics(String from, String to, ArrayList<Subject> subjectList, String type) throws Exception;
    
    public ArrayList<ItemDashboard> getRegistrationStatistics(String from, String to) throws Exception;

    public ArrayList<ItemDashboard> getRevenueStatistics(String from, String to) throws Exception;

    public ArrayList<ItemDashboard> getRevenueStatisticsBySubjectCate(String from, String to) throws Exception;
    
    public ArrayList<Registration> get10NewRegistration() throws Exception;

    public ArrayList<String> convertJson(ArrayList<ItemDashboard> viewList) throws Exception;

    public ArrayList<String> getNameList(ArrayList<ItemDashboard> viewList) throws Exception;

    public ArrayList<Subject> getRegistedSubject(int userId) throws Exception;

    public ArrayList<Subject> getRegistedSubjectbyUserId(int userId) throws Exception;
    
    public ArrayList<Registration> getPaidRegistration(String type) throws Exception;
    
    public ArrayList<Registration> getFilterRegistration(String type, String value) throws Exception;
    
    public ArrayList<RegistrationManage> getFilterRegistration(int subjectId, int userId) throws Exception;
}
