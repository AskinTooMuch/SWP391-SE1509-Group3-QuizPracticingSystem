/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  TestTypeDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 */
package dao;

import bean.TestType;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của TestTypeDAOImpl
 *
 * @author NamDH
 */
public interface TestTypeDAO {

    /**
     * Get all testType in the database
     *
     * @return <code>ArrayList<TestType></code>
     * @throws Exception
     */
    public ArrayList<TestType> getAllTestTypes() throws Exception;

    public TestType getTestTypeById(int ttId) throws Exception;

    public int updateTestType(TestType updatedTestType) throws Exception;

    public int deleteTestType(int ttId) throws Exception;

    public int addTestType(TestType newTestType) throws Exception;
}
