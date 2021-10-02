/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Slider;
import dao.DBConnection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import dao.SliderDAO;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class SliderDAOImpl extends DBConnection implements SliderDAO{

    /*Get all Slider from table*/
    @Override
    public ArrayList<Slider> getSlider() throws Exception {
        Connection conn = null;
        ResultSet rs = null;    /* Result set returned by the sqlserver */
        PreparedStatement pre = null;   /* Prepared statement for executing sql queries */
        
        ArrayList<Slider> allSlider = new ArrayList();
        String sql = "SELECT * FROM [Slider]";
            /* Get the slider */
            try {
            conn = getConnection(); 
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int sliderId = rs.getInt("sliderId");
                String sliderTitle = rs.getString("sliderTitle");
                String image = rs.getString("image");
                String link = rs.getString("link");
                String note = rs.getString("note");
                Boolean status = rs.getBoolean("status");
                
                allSlider.add(new Slider(sliderId, sliderTitle, image, link, note, true));
            }
            } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
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
