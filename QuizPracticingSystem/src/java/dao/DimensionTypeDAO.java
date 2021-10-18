/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  DimensionTypeDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.DimensionType;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của DimensionTypeDAOImpl
 *
 * @author NamDH
 */
public interface DimensionTypeDAO {
    
    public ArrayList<DimensionType> getAllDimensionTypes() throws Exception;
    
    public DimensionType getDimensionTypeById(int dtId) throws Exception;
    
    public int updateDimensionType (DimensionType updatedDimensionType) throws Exception;
    
    public int deteteDimensionTyoe(int dtId) throws Exception;
    
    public int addDimensionType(DimensionType newDimensionType ) throws Exception;
}
