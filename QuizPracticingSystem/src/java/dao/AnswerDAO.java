/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  AnswerDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 */
package dao;

import bean.Answer;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của AnswerDAOImpl
 *
 * @author NamDH
 */
public interface AnswerDAO {
    
    /**
     * get all answers
     * @return
     * @throws Exception 
     */
    public ArrayList<Answer> getAllAnswers() throws Exception;

    /**
     * get Answer by QuestionId
     *
     * @param questionId the target question id. It is a <code>int</code>
     * primitive
     * @return a list of Answer. It is a <code>java.util.ArrayList</code>
     * @throws Exception
     */
    public ArrayList<Answer> getAnswersByQuenstionId(int questionId) throws Exception;
    
    /**
     * 
     * @param aId
     * @return
     * @throws Exception 
     */
    public int deleteAnswerById(int aId) throws Exception;
    
    /**
     * 
     * @param qId
     * @return
     * @throws Exception 
     */
    public int deleteAnswerByQuestionId(int qId) throws Exception;

    /**
     * Update Answer
     *
     * @param answerId It is a <code>int</code>
     * @param updatedAnswer It is a <code>object</code>
     * @return check. It is a <code>int</code>
     * @throws Exception
     */
    public int updateAnswer(int answerId, Answer updatedAnswer) throws Exception;

    /**
     * Add New Answer
     *
     * @param newAnswer It is a <code>Object</code> primitive type
     * @return count. It is a <code>int</code>
     * @throws Exception
     */
    public int addAnswer(Answer newAnswer) throws Exception;
    
    /**
     * Get answers by Id 
     * @param answerId It is a <code>int</code>
     * @return 
     * @throws Exception 
     */
    public Answer getAnswersById(int answerId) throws Exception;
}
