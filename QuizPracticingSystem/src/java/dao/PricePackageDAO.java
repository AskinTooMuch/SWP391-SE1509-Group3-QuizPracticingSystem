/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.PricePackage;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface PricePackageDAO {
    
    public ArrayList<PricePackage> getAllPricePackage() throws Exception;
    
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId) throws Exception;
    
    public PricePackage getPricePackageById(int ppId) throws Exception;
    
    public int addPricePackage(PricePackage newPricePackage) throws Exception;
    
    public int updatePricePackage(PricePackage updatedPricePackage) throws Exception;
    
    public int deletePricePackage(int ppId) throws Exception;
}
