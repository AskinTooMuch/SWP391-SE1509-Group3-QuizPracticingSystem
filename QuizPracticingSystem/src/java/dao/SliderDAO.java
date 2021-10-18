/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SliderDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
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
