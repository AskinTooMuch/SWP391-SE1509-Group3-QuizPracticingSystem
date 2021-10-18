/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Dimension entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 */
package dao;

import bean.Question;
import bean.Quiz;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuizDAOImpl
 *
 * @author NamDH
 */
public interface QuizDAO {

    /**
     * Find all quiz in the database
     *
     * @return <code>ArrayList<Quiz></code>
     * @throws Exception
     */
    public ArrayList<Quiz> getAllQuiz() throws Exception;

    /**
     * get quiz by Id
     *
     * @param quizId the target quiz's id. It is a <code>int</code> primitive
     * type
     * @return a quiz <code>Quiz</code> object.
     * @throws java.lang.Exception
     */
    public Quiz getQuizById(int quizId) throws Exception;

    /**
     * get taken quiz by takeQuiz's Id
     *
     * @param quizTakeId the target quiz's id. It is a <code>int</code>
     * primitive type
     * @return a quiz <code>Quiz</code> object.
     * @throws java.lang.Exception
     */
    public Quiz getQuizByQuizTakeId(int quizTakeId) throws Exception;

    public ArrayList<Quiz> getQuizBySubject(int subjectId) throws Exception;

    public ArrayList<Quiz> getQuizByLesson(int lessonId) throws Exception;

    /**
     * get all simulation quiz by user search
     *
     * @param userId user's id. <code>int</code> primitive type
     * @param subjectId user's registered subject's id. <code>int</code>
     * primitive type
     * @param quizName search string to search quiz by name. <code>String</code>
     * primitive type
     * @return all simulation quiz by user. <code>ArrayList<Quiz></code> object
     * @throws Exception
     */
    public ArrayList<Quiz> getAllSimulationQuizByUser(int userId, int subjectId, String quizName) throws Exception;

    public int editQuiz(int quizId, Quiz quiz) throws Exception;

    public int addQuiz(Quiz quiz) throws Exception;

    public int deleteQuiz(int quizId) throws Exception;

    public int getQuizIdCreated(Quiz quiz) throws Exception;

    public int addQuizQuestion(int quizId, int questionId) throws Exception;

    public ArrayList<Quiz> getFilteredQuiz(int subjectId, int quizTypeId) throws Exception;

    public ArrayList<Quiz> getQuizByName(String searchName) throws Exception;

    public int removeQuizQuestion(int quizId) throws Exception;
}
