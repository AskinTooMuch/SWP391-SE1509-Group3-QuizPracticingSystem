/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  QuestionQuizHandleDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
 */
package dao;

import bean.Answer;
import bean.QuestionQuizHandle;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuestionQuizHandleDAOImpl
 *
 * @author NamDH
 */
public interface QuestionQuizHandleDAO {

    /**
     * turn a Question into QuestionQUizHandle type
     *
     * @param questionId the target question's id. It is a <code>int</code>
     * primitive type
     * @return a QuestionQuizHandle <code>QuestionQuizHandle</code> object.
     * @throws java.lang.Exception
     */
    public QuestionQuizHandle generateQuestionById(int questionId) throws Exception;

    /**
     * get right answer of the question
     *
     * @param question the target question's id. It is a
     * <code>QuestionQuizHandle</code> object
     * @return right answer of the question. It is <code>Answer</code> object.
     * @throws java.lang.Exception
     */
    public Answer getRightAnswer(QuestionQuizHandle question) throws Exception;

    /**
     * get the taken quiz for review action
     *
     * @param quizTakeId the target taken quiz's id. It is a <code>int</code>
     * primitive type
     * @return a <code>QuizQuizHandle</code> object.
     * @throws java.lang.Exception
     */
    public ArrayList<QuestionQuizHandle> getReviewQuestion(int quizTakeId) throws Exception;

    /**
     * mark and unmark question
     *
     * @param question the target question's id. It is a
     * <code>QuestionQuizHandle</code> object
     * @return void.
     * @throws java.lang.Exception
     */
    public void markQuestion(QuestionQuizHandle question) throws Exception;
}
