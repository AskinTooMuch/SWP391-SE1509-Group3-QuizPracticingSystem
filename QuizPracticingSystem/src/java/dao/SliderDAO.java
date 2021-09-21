/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Slider;
import dao.impl.MyDAO;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class SliderDAO extends MyDAO {

    public ArrayList<Slider> getSlider(String title, String link, boolean status) {
        return null;
    }

    public Slider getSliderById(int sliderId) {
        return null;
    }

    public int editSlider(int sliderId,Slider editedSlider) {
        return 0;
    }

    public int addSlider(Slider newSlder) {
        return 0;
    }
    public int deleteSlider(int sliderId){
        return 0;
    }
}
