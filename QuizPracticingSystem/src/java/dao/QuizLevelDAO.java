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
     * get all quizlevel in the database
     *
     * @return <code>ArrayList<QuizLevel></code>
     * @throws Exception
     */
    public ArrayList<QuizLevel> getAllQuizLevel() throws Exception;

    public QuizLevel getQuizLevelById(int quizLevelId) throws Exception;

    public int editQuizLevel(int quizLevelId, QuizLevel quizLevel) throws Exception;

    public int addQuizLevel(QuizLevel quizLevel) throws Exception;

    public int deleteQuizLevel(int quizLevelId) throws Exception;
}
