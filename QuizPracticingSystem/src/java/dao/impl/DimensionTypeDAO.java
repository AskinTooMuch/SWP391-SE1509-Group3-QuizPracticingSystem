/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.DimensionType;
import dao.DimensionTypeINT;
import dao.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public class DimensionTypeDAO extends MyDAO implements DimensionTypeINT{
    
    @Override
    public ArrayList<DimensionType> getAllDimensionTypes(){
        return null;
    }
    
    @Override
    public DimensionType getDimensionTypeById(int dtId){
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
