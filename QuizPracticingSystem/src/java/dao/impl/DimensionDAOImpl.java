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

import bean.Dimension;
import dao.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.DimensionDAO;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class DimensionDAOImpl extends DBConnection implements DimensionDAO {

    @Override
    public ArrayList<Dimension> getAllDimension() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

 /* Get dimension list of the subject */
        ArrayList<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT S.[subjectId]\n"
                + "      ,D.[dimensionId]\n"
                + "      ,D.[subjectId]\n"
                + "      ,D.[dimensionTypeId]\n"
                + "      ,D.[dimensionName]\n"
                + "      ,D.[description]\n"
                + "      ,D.[status]\n"
                + "	  ,DT.[dimensionTypeName]\n"
                + "  FROM [QuizSystem].[dbo].[Subject] S \n"
                + "  INNER JOIN [QuizSystem].[dbo].[Dimension] D ON S.subjectId = D.subjectId \n"
                + "  INNER JOIN [QuizSystem].[dbo].DimensionType DT ON DT.dimensionTypeId = D.dimensionTypeId\n"
                + "  WHERE S.subjectId =" + subjectId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                dimensions.add(new Dimension(rs.getInt("dimensionId"),
                        subjectId,
                        rs.getInt("dimensionTypeId"),
                        rs.getString("dimensionTypeName"),
                        rs.getString("dimensionName"),
                        rs.getString("description"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return dimensions;
    }

    @Override
    public Dimension getDimensionById(int dimensionId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        Dimension dimensionById = null;
        String sql = "SELECT * FROM [Dimension] WHERE [dimensionId] = " + dimensionId;

        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                int dimensionTypeId = rs.getInt("dimensionTypeId");
                String dimensionName = rs.getString("dimensionName");
                String description = rs.getString("description");
                Boolean status = rs.getBoolean("status");

                dimensionById = new Dimension(dimensionId, subjectId, dimensionTypeId, dimensionName, dimensionName, description, true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return dimensionById;
    }

    @Override
    public int addDimension(Dimension dimension) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteDimension(int dimensionId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editDimension(int dimensionId, Dimension dimension) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
