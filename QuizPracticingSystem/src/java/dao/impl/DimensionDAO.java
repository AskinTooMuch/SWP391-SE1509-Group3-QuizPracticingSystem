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
import dao.DimensionINT;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class DimensionDAO extends MyDAO implements DimensionINT{

    @Override
    public ArrayList<Dimension> getAllDimension() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) {
        /* Get dimension list of the subject */
        ArrayList<Dimension> dimensions = new ArrayList<>();
        String sql = "SELECT S.[subjectId]\n" +
                    "      ,D.[dimensionId]\n" +
                    "      ,D.[subjectId]\n" +
                    "      ,D.[dimensionTypeId]\n" +
                    "      ,D.[dimensionName]\n" +
                    "      ,D.[description]\n" +
                    "      ,D.[status]\n" +
                    "	  ,DT.[dimensionTypeName]\n" +
                    "  FROM [QuizSystem].[dbo].[Subject] S \n" +
                    "  INNER JOIN [QuizSystem].[dbo].[Dimension] D ON S.subjectId = D.subjectId \n" +
                    "  INNER JOIN [QuizSystem].[dbo].DimensionType DT ON DT.dimensionTypeId = D.dimensionTypeId\n" +
                    "  WHERE S.subjectId =" + subjectId;
        try {
        PreparedStatement pre = conn.prepareStatement(sql);
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
        } catch (SQLException e) {
        System.out.println(e);
        }
        return dimensions; 
    }

    @Override
    public Dimension getDimensionById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addDimension(Dimension dimension) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteDimension(int dimensionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editDimension(int dimensionId, Dimension dimension) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    public static void main(String[] args) {
//        DimensionDAO dimensionDao = new DimensionDAO();
//        System.out.println(dimensionDao.getDimensionBySubject(4).size());
//    }
}
