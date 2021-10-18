/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  LessonTypeDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.LessonType;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của LessonTypeDAOImpl
 *
 * @author NamDH
 */
public interface LessonTypeDAO {
    
    public ArrayList<LessonType> getAllLessonType() throws Exception;
    
    public LessonType getLessonTypeById(int ltId) throws Exception;
    
    public int addLessonType(LessonType newLessonType) throws Exception;
    
    public int updateLessonType(LessonType updatedLessonType) throws Exception;
    
    public int deleteLessonType(int ltId) throws Exception;
    
}
