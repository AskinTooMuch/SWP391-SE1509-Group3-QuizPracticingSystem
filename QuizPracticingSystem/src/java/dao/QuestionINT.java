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
public interface QuestionINT {

    ArrayList<Question> getAllQuestion();

    public Question getQuestionById(int questionId);

    public int addQuestion(Question newQuestion);

    public int editQuestion(int questionId, Question editedQuestion);

    public int deleteQuestion(int questionId);

    public int importQuestion(ArrayList<Question> questionList);
}
