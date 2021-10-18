/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  PricePackageDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.PricePackage;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của PricePackageDAOImpl
 *
 * @author NamDH
 */
public interface PricePackageDAO {
    
    public ArrayList<PricePackage> getAllPricePackage() throws Exception;
    
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId) throws Exception;
    
    public PricePackage getPricePackageById(int ppId) throws Exception;
    
    public int addPricePackage(PricePackage newPricePackage) throws Exception;
    
    public int updatePricePackage(PricePackage updatedPricePackage) throws Exception;
    
    public int deletePricePackage(int ppId) throws Exception;
}
