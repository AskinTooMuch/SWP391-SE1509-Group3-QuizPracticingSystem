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
    
    public ArrayList<Answer> getAllAnswers();
    
    public ArrayList<Answer> getAnswersByQuenstionId(int qId);
    
    public int deleteAnswerById(int aId);
    
    public int deleteAnswerByQuestionId( int qId);
    
    public int updateAnswer(Answer updatedAns);
    
    public int addAnswer(Answer newAns);
}
