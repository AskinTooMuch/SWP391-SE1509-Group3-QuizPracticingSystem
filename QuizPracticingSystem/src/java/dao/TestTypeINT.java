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
public interface TestTypeINT {
    
    public ArrayList<TestType> getAllTestTypes();
    
    public TestType getTestTypeById(int ttId);
    
    public int updateTestType (TestType updatedTestType);
    
    public int deleteTestType(int ttId);
    
    public int addTestType(TestType newTestType);
}
