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
public interface DimensionDAO {
    public ArrayList<Dimension> getAllDimension() throws Exception;
    
    public ArrayList<Dimension> getDimensionBySubject(int subjectId) throws Exception;
    
    public Dimension getDimensionById(int dimensionId) throws Exception;
    
    public int addDimension(Dimension dimension) throws Exception;
    
    public int deleteDimension(int dimensionId) throws Exception;
    
    public int editDimension(int dimensionId, Dimension dimension) throws Exception;
}
