/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.TestType;
import dao.MyDAO;
import dao.TestTypeINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public class TestTypeDAO extends MyDAO implements TestTypeINT {

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
