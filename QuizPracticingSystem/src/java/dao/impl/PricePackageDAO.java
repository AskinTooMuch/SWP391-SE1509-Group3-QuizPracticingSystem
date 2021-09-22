/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.PricePackage;
import java.util.ArrayList;
import dao.MyDAO;

/**
 *
 * @author tuan
 */
public class PricePackageDAO extends MyDAO{
    public ArrayList<PricePackage> getAllPricePackage(){
        return null;
    }
    
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId){
        return null;
    }
    
    public PricePackage getPricePackageById(int ppId){
        return null;
    }
    
    public int addPricePackage(PricePackage newPricePackage){
        return 0;
    }
    
    public int updatePricePackage(PricePackage updatedPricePackage){
        return 0;
    }
    
    public int deletePricePackage(int ppId){
        return 0;
    }
}
