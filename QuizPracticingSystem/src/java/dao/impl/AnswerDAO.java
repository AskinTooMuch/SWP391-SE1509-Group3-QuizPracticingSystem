/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import bean.*;
import dao.AnswerINT;
import dao.MyDAO;
import java.util.ArrayList;
/**
 *
 * @author duong
 */
public class AnswerDAO extends MyDAO implements AnswerINT{
    
    @Override
    public ArrayList<Answer> getAllAnswers(){
        return null;
    }
    
    @Override
    public ArrayList<Answer> getAnswersByQuenstionId(int qId){
        return null;
    }
    
    @Override
    public int deleteAnswerById(int aId){
        return 0;
    }
    
    @Override
    public int deleteAnswerByQuestionId( int qId) {
        return 0;
    }
    
    @Override
    public int updateAnswer(Answer updatedAns){
        return 0;
    }
    
    @Override
    public int addAnswer(Answer newAns){
        return 0;
    }
}
