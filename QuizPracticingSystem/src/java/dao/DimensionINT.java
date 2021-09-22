/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Dimension;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface DimensionINT {
    public ArrayList<Dimension> getAllDimension();
    
    public ArrayList<Dimension> getDimensionBySubject(int subjectId);
    
    public Dimension getDimensionById();
    
    public int addDimension(Dimension dimension);
    
    public int deleteDimension(int dimensionId);
    
    public int editDimension(int dimensionId, Dimension dimension);
}
