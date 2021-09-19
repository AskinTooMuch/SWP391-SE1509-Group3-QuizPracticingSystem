/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import bean.*;
import java.util.ArrayList;
/**
 *
 * @author duong
 */
public class AnswerDAO extends MyDAO{
    
    public ArrayList<Answer> getAllAnswers(){
        return null;
    }
    
    public ArrayList<Answer> getAnswersByQuenstionId(int qId){
        return null;
    }
    
    public int deleteAnswerById(int aId){
        return 0;
    }
    
    public int deleteAnswerByQuestionId( int qId) {
        return 0;
    }
    
    public int updateAnswer(Answer updatedAns){
        return 0;
    }
    
    public int addAnswer(Answer newAns){
        return 0;
    }
}
