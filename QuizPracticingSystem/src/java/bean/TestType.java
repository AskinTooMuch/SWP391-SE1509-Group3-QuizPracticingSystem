/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Answer entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         DuongNHHE150328     First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
*/
package bean;

/**
 * TestType entity
 *
 * @author duong
 */
public class TestType {
    private int testTypeId; /*Test type id*/
    private String testTypeName; /*Test type name*/
    private boolean status; /*Test type status*/

    /**
     * Complete constructor
     * @param testTypeId
     * @param testTypeName
     * @param status 
     */
    public TestType(int testTypeId, String testTypeName, boolean status) {
        this.testTypeId = testTypeId;
        this.testTypeName = testTypeName;
        this.status = status;
    }

    /**
     * Get testTypeID
     * @return 
     */
    public int getTestTypeId() {
        return testTypeId;
    }

    /**
     * Get testTypeName
     * @return 
     */
    public String getTestTypeName() {
        return testTypeName;
    }

    /**
     * Get status
     * @return 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set TestTypeId
     * @param testTypeId 
     */
    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    /**
     * Set testTypeName
     * @param testTypeName 
     */
    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    /**
     * Set Status
     * @param status 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
