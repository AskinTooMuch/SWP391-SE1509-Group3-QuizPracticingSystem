/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author duong
 */
public class TestType {
    private int testTypeId;
    private String testTypeName;
    private boolean status;

    public TestType(int testTypeId, String testTypeName, boolean status) {
        this.testTypeId = testTypeId;
        this.testTypeName = testTypeName;
        this.status = status;
    }

    public int getTestTypeId() {
        return testTypeId;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId = testTypeId;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
