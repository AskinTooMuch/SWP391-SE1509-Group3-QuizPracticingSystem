/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         DuongNHHE150328  First Deploy
*/
package dao.impl;

import bean.TestType;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.TestTypeDAO;

/**
 *
 * @author duong
 */
public class TestTypeDAOImpl extends MyDAO implements TestTypeDAO {

    @Override
    public ArrayList<TestType> getAllTestTypes() {
        return null;
    }

    @Override
    public TestType getTestTypeById(int testTypeId) {
        String sql = "SELECT * FROM [TestType] WHERE testTypeId =" + testTypeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new TestType(rs.getInt("testTypeId"),
                        rs.getString("testTypeName"),
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public int updateTestType(TestType updatedTestType) {
        return 0;
    }

    @Override
    public int deleteTestType(int testTypeId) {
        return 0;
    }

    @Override
    public int addTestType(TestType newTestType) {
        return 0;
    }
}
