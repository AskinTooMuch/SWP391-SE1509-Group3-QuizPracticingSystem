/*
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  UserController map
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author           Description
 *  23/9/21     1.0         DuongNHHE150328  First Deploy
 *  07/10/21    1.1         DuongNHHE150328  Add method
 */
package dao.impl;

import bean.DimensionType;
import dao.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.DimensionTypeDAO;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class DimensionTypeDAOImpl extends DBConnection implements DimensionTypeDAO {

    /**
     * 
     * @return <code>ArrayList</code>
     * @throws Exception 
     */
    @Override
    public ArrayList<DimensionType> getAllDimensionTypes() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        ArrayList<DimensionType> dimensionTypesList = new ArrayList<>();
        String sql = "SELECT [dimensionTypeId]\n"
                + "      ,[dimensionTypeName]\n"
                + "      ,[status]\n"
                + "  FROM [QuizSystem].[dbo].[DimensionType]\n"
                + "  WHERE status = 1 ";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                dimensionTypesList.add(
                        new DimensionType(rs.getInt("dimensionTypeId"),
                                rs.getString("dimensionTypeName"),
                                rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return dimensionTypesList;
    }

    @Override
    public DimensionType getDimensionTypeById(int dimensionTypeId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        String sql = "SELECT * FROM [DimensionType] WHERE dimensionTypeId=" + dimensionTypeId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new DimensionType(rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
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
    public int updateDimensionType(DimensionType updatedDimensionType) throws Exception {
        return 0;
    }

    @Override
    public int deteteDimensionTyoe(int dtId) throws Exception {
        return 0;
    }

    @Override
    public int addDimensionType(DimensionType newDimensionType) throws Exception {
        return 0;
    }
}
