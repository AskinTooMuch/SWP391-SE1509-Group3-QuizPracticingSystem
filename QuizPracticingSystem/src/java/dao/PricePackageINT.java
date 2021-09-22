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
public interface PricePackageINT {
    public ArrayList<PricePackage> getAllPricePackage();
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId);
    public PricePackage getPricePackageById(int ppId);
    public int addPricePackage(PricePackage newPricePackage);
    public int updatePricePackage(PricePackage updatedPricePackage);
    public int deletePricePackage(int ppId);
}
