/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.TestType;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface TestTypeDAO {
    
    public ArrayList<TestType> getAllTestTypes() throws Exception;
    
    public TestType getTestTypeById(int ttId) throws Exception;
    
    public int updateTestType (TestType updatedTestType) throws Exception;
    
    public int deleteTestType(int ttId) throws Exception;
    
    public int addTestType(TestType newTestType) throws Exception;
}
