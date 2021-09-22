/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.PricePackage;
import java.util.ArrayList;
import dao.MyDAO;
import dao.PricePackageINT;

/**
 *
 * @author tuan
 */
public class PricePackageDAO extends MyDAO implements PricePackageINT{
    @Override
    public ArrayList<PricePackage> getAllPricePackage(){
        return null;
    }
    
    @Override
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId){
        return null;
    }
    
    @Override
    public PricePackage getPricePackageById(int ppId){
        return null;
    }
    
    @Override
    public int addPricePackage(PricePackage newPricePackage){
        return 0;
    }
    
    @Override
    public int updatePricePackage(PricePackage updatedPricePackage){
        return 0;
    }
    
    @Override
    public int deletePricePackage(int ppId){
        return 0;
    }
}
