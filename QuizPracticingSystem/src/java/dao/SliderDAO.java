/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SliderDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 */
package dao;

import bean.Slider;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của SliderDAOImpl
 *
 * @author NamDH
 */
public interface SliderDAO {

    /**
     * get all slider in database
     *
     * @return <code>ArrayList<Slider><code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Slider> getSlider() throws Exception;

}
