/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.DimensionType;
import dao.impl.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public class DimensionTypeDAO extends MyDAO{
    
    public ArrayList<DimensionType> getAllDimensionTypes(){
        return null;
    }
    
    public DimensionType getDimensionTypeById(int dtId){
        return null;
    }
    
    public int updateDimensionType (DimensionType updatedDimensionType){
        return 0;
    }
    
    public int deteteDimensionTyoe(int dtId){
        return 0;
    }
    
    public int addDimensionType(DimensionType newDimensionType ){
        return 0;
    }
}
