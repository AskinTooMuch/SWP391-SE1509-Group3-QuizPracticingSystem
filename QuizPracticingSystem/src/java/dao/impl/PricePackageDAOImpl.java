/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.PricePackage;
import dao.DBConnection;
import java.util.ArrayList;
import dao.MyDAO;
import dao.PricePackageDAO;

/**
 *
 * @author tuan
 */
public class PricePackageDAOImpl extends DBConnection implements PricePackageDAO{
    
    @Override
    public ArrayList<PricePackage> getAllPricePackage() throws Exception{
        return null;
    }
    
    @Override
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId) throws Exception{
        return null;
    }
    
    @Override
    public PricePackage getPricePackageById(int ppId) throws Exception{
        return null;
    }
    
    @Override
    public int addPricePackage(PricePackage newPricePackage) throws Exception{
        return 0;
    }
    
    @Override
    public int updatePricePackage(PricePackage updatedPricePackage) throws Exception{
        return 0;
    }
    
    @Override
    public int deletePricePackage(int ppId) throws Exception{
        return 0;
    }
}
