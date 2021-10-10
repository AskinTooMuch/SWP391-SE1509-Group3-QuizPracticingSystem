/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         DuongNHHE150328  First Deploy
 *  10/10/21    1.1         DuongNHHE150328  Add new method
 */
package dao.impl;

import bean.TestType;
import dao.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.TestTypeDAO;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class TestTypeDAOImpl extends DBConnection implements TestTypeDAO {

    @Override
    public ArrayList<TestType> getAllTestTypes() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        TestType testType = null;
        ArrayList<TestType> testTypeList = new ArrayList<>();
        String sql = "SELECT [testTypeId]\n"
                + "      ,[testTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[TestType]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                testType = new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
                testTypeList.add(testType);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return testTypeList;
    }

    @Override
    public TestType getTestTypeById(int testTypeId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "SELECT * FROM [TestType] WHERE testTypeId =" + testTypeId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    @Override
    public int updateTestType(TestType updatedTestType) throws Exception {
        return 0;
    }

    @Override
    public int deleteTestType(int testTypeId) throws Exception {
        return 0;
    }

    @Override
    public int addTestType(TestType newTestType) throws Exception {
        return 0;
    }
}
