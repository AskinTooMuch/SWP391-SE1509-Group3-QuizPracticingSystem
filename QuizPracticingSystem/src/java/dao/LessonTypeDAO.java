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
 *  24/10/21    1.2         DuongNHHE150328     Add method
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
    /**
     * get all lesson type from the database have status = 1
     * @return
     * @throws Exception 
     */
    public ArrayList<LessonType> getAllLessonType() throws Exception;
    
    /**
     * get lesson type by id
     * @param ltId
     * @return
     * @throws Exception 
     */
    public LessonType getLessonTypeById(int ltId) throws Exception;
    
    /**
     * insert new lesson type
     * @param newLessonType
     * @return
     * @throws Exception 
     */
    public int addLessonType(LessonType newLessonType) throws Exception;
    
    /**
     * update existed lesson type
     * @param updatedLessonType
     * @return
     * @throws Exception 
     */
    public int updateLessonType(LessonType updatedLessonType) throws Exception;
    
    /**
     * delete existed lesson 
     * @param lessonTypeId
     * @return
     * @throws Exception 
     */
    public int deleteLessonType(int lessonTypeId) throws Exception;
    
     /**
     * get all lesson type from the database
     * @return
     * @throws Exception 
     */
    public ArrayList<LessonType> getAllStatusLessonType() throws Exception;
}
