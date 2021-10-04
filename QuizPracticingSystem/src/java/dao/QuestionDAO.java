/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Question;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public interface QuestionDAO {

    ArrayList<Question> getAllQuestion() throws Exception;

    public Question getQuestionById(int questionId) throws Exception;
    
    public ArrayList<Question> getQuestionByQuizId(int quizId) throws Exception;
    
    public ArrayList<Question> getQuestionByContent(String content) throws Exception;

    public int addQuestion(Question newQuestion) throws Exception;

    public int editQuestion(int questionId, Question editedQuestion) throws Exception;

    public int deleteQuestion(int questionId) throws Exception;

    public int importQuestion(ArrayList<Question> questionList) throws Exception;
}
