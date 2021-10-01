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
    public ArrayList<Slider> getSlider();
    public Slider getSliderById(int sliderId);
    public int editSlider(int sliderId,Slider editedSlider);
    public int addSlider(Slider newSlder);
    public int deleteSlider(int sliderId);

}
