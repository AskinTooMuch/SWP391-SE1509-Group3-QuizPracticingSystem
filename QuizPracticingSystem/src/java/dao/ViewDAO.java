/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.ItemDashboard;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public interface ViewDAO {

    public int updateView() throws Exception;
    
    public boolean checkCurrentDateViewExist() throws Exception;

    public ArrayList<ItemDashboard> getViewStatistics(String from, String to) throws Exception;

    public ArrayList<String> convertJson(ArrayList<ItemDashboard> views) throws Exception;

    public ArrayList<String> getNameList(ArrayList<ItemDashboard> viewList) throws Exception;

}
