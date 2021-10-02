/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Slider;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface SliderDAO {
    
    public ArrayList<Slider> getSlider() throws Exception;
    
    public Slider getSliderById(int sliderId) throws Exception;
    
    public int editSlider(int sliderId,Slider editedSlider) throws Exception;
    
    public int addSlider(Slider newSlder) throws Exception;
    
    public int deleteSlider(int sliderId) throws Exception;

}
