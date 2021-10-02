/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.DimensionType;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface DimensionTypeDAO {
    
    public ArrayList<DimensionType> getAllDimensionTypes() throws Exception;
    
    public DimensionType getDimensionTypeById(int dtId) throws Exception;
    
    public int updateDimensionType (DimensionType updatedDimensionType) throws Exception;
    
    public int deteteDimensionTyoe(int dtId) throws Exception;
    
    public int addDimensionType(DimensionType newDimensionType ) throws Exception;
}
