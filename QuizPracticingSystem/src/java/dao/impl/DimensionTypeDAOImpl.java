/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    @Override
    public ArrayList<DimensionType> getAllDimensionTypes() throws Exception {
        return null;
    }

    @Override
    public DimensionType getDimensionTypeById(int dimensionTypeId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */

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
