/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Answer;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface AnswerDAO {
    
    public ArrayList<Answer> getAllAnswers() throws Exception;
    
    public ArrayList<Answer> getAnswersByQuenstionId(int qId) throws Exception;
    
    public int deleteAnswerById(int aId) throws Exception;
    
    public int deleteAnswerByQuestionId( int qId) throws Exception;
    
    public int updateAnswer(int answerId, Answer updatedAnswer) throws Exception;
    
    public int addAnswer(Answer newAns) throws Exception;
    
    public Answer getAnswersById(int answerId) throws Exception;
}
