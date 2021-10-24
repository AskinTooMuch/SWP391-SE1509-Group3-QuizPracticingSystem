/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  QuizLevelDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 *  24/10/21    1.2         DuongNHHE150328  Add method
 */
package dao;

import bean.QuizLevel;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuizLevelDAOImpl
 *
 * @author NamDH
 */
public interface QuizLevelDAO {

    /**
     * get all quizlevel in the database where status = 1 
     *
     * @return <code>ArrayList<QuizLevel></code>
     * @throws Exception
     */
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception;

    /**
     * get quiz level by id
     * @param quizLevelId
     * @return
     * @throws Exception 
     */
    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception;

    /**
     * update existed quiz level
     * @param quizLevelId
     * @param quizLevel
     * @return
     * @throws Exception 
     */
    public int editQuizLevel(QuizLevel quizLevel) throws Exception;

    /**
     * add new quiz level to the database
     * @param quizLevel
     * @return
     * @throws Exception 
     */
    public int addQuizLevel(QuizLevel quizLevel) throws Exception;

    /**
     * delete a quiz level from a database
     * @param quizLevelId
     * @return
     * @throws Exception 
     */
    public int deleteQuizLevel(int quizLevelId) throws Exception;
    
    /**
     * get all quizlevel in the database
     *
     * @return <code>ArrayList<QuizLevel></code>
     * @throws Exception
     */
    public ArrayList<QuizLevel> getAllStatusQuizLevel() throws Exception;
}
