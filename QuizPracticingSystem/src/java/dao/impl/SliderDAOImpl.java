/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Slider;
import dao.MyDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.SliderDAO;

/**
 *
 * @author admin
 */
public class SliderDAOImpl extends MyDAO implements SliderDAO{

    /*Get all Slider from table*/
    @Override
    public ArrayList<Slider> getSlider() throws Exception {
        ArrayList<Slider> allSlider = new ArrayList();
        String sql = "SELECT * FROM [Slider]";
            /* Get the slider */
            PreparedStatement preSlider = conn.prepareStatement(sql);
            rs = preSlider.executeQuery();
            while (rs.next()) {
                int sliderId = rs.getInt("sliderId");
                String sliderTitle = rs.getString("sliderTitle");
                String image = rs.getString("image");
                String link = rs.getString("link");
                String note = rs.getString("note");
                Boolean status = rs.getBoolean("status");
                
                allSlider.add(new Slider(sliderId, sliderTitle, image, link, note, true));
            }
        return allSlider;

    }

    @Override
    public Slider getSliderById(int sliderId) throws Exception {
        return null;
    }

    @Override
    public int editSlider(int sliderId,Slider editedSlider) throws Exception {
        return 0;
    }

    @Override
    public int addSlider(Slider newSlder) throws Exception {
        return 0;
    }
    @Override
    public int deleteSlider(int sliderId) throws Exception{
        return 0;
    }
}
