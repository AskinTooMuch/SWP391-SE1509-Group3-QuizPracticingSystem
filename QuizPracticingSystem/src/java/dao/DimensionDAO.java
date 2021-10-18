/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  DimensionDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.*;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của DimensionDAOImpl
 *
 * @author NamDH
 */
public interface DimensionDAO {
    public ArrayList<Dimension> getAllDimension() throws Exception;
    
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception;
    
    public Dimension getDimensionById(int dimensionId) throws Exception;
    
    public int addDimension(Dimension dimension) throws Exception;
    
    public int deleteDimension(int dimensionId) throws Exception;
    
    public int editDimension(int dimensionId, Dimension dimension) throws Exception;
    
    public ArrayList<DimensionType> getSubjectDimensionType(int subjectId) throws Exception;
}
