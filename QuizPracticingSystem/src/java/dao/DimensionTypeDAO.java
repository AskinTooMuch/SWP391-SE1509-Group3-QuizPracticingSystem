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
 *  24/10/21    1.0         DuongNHHE150328     Add method
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
    
    /**
     * Get all dimension type with status = 1
     * @return <code>ArrayList</code>
     * @throws Exception 
     */
    public ArrayList<DimensionType> getAllDimensionTypes() throws Exception;
    
    /**
     * Get dimension type by a specified id
     * @param dimensionTypeId
     * @return
     * @throws Exception 
     */
    public DimensionType getDimensionTypeById(int dimensionTypeId) throws Exception;
    
    /**
     * Update dimension type
     * @param updatedDimensionType
     * @return
     * @throws Exception 
     */
    public int updateDimensionType (DimensionType updatedDimensionType) throws Exception;
    
    /**
     * Delete dimension type
     * @param dtId dimensionID
     * @return
     * @throws Exception 
     */
    public int deteteDimensionType(int dtId) throws Exception;
    
    /**
     * Add new dimension Type
     * @param newDimensionType
     * @return
     * @throws Exception 
     */
    public int addDimensionType(DimensionType newDimensionType ) throws Exception;
    
    /**
     * Get all dimension type
     * @return <code>ArrayList</code>
     * @throws Exception 
     */
    public ArrayList<DimensionType> getAllStatusDimensionTypes() throws Exception;
}
