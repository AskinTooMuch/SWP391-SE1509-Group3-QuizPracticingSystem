/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.DimensionType;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.DimensionTypeDAO;

/**
 *
 * @author duong
 */
public class DimensionTypeDAOImpl extends MyDAO implements DimensionTypeDAO{
    
    @Override
    public ArrayList<DimensionType> getAllDimensionTypes(){
        return null;
    }
    
    @Override
    public DimensionType getDimensionTypeById(int dimensionTypeId){
        String sql="SELECT * FROM [DimensionType] WHERE dimensionTypeId="+dimensionTypeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
               return new DimensionType(rs.getInt("dimensionTypeId"),
               rs.getString("dimensionTypeName"),
               rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    @Override
    public int updateDimensionType (DimensionType updatedDimensionType){
        return 0;
    }
    
    @Override
    public int deteteDimensionTyoe(int dtId){
        return 0;
    }
    
    @Override
    public int addDimensionType(DimensionType newDimensionType ){
        return 0;
    }
}