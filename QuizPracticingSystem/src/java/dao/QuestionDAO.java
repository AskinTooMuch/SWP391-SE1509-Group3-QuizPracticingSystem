/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  QuestionDAO Interface
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import bean.*;
import java.util.ArrayList;

/**
 * Lớp này chứa các interface của QuestionDAOImpl
 *
 * @author NamDH
 */
public interface QuestionDAO {

    ArrayList<Question> getAllQuestion() throws Exception;

    public Question getQuestionById(int questionId) throws Exception;
    
    public ArrayList<Question> getQuestionByQuizId(int quizId) throws Exception;
    
    public ArrayList<QuestionManage> getQuestionByContent(String content) throws Exception;

    public int addQuestion(Question newQuestion) throws Exception;

    public int editQuestion(int questionId, Question editedQuestion) throws Exception;

    public int deleteQuestion(int questionId) throws Exception;

    public int importQuestion(ArrayList<Question> questionList) throws Exception;
    
    public ArrayList<QuestionManage> getQuestionManage(int subjectId, int dimensionId, int lessonId) throws Exception;
    
    public ArrayList<Question> getQuestionForCreateQuiz(int numberOfQuestion,int subjectId, int dimensionId) throws Exception;

    public int getQuestionIdCreated(Question question) throws Exception;
}
