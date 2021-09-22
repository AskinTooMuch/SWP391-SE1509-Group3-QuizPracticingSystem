/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Question;
import java.util.ArrayList;
import dao.MyDAO;

/**
 *
 * @author admin
 */
public class QuestionDAO extends MyDAO {

    public ArrayList<Question> getQuestion(String content, int subjectId, int lessonId, boolean status) {
        return null;
    }

    public Question getQuestionById(int questionId) {
        return null;
    }

    public int addQuestion(Question newQuestion) {
        return 0;
    }
    public int editQuestion(int questionId, Question editedQuestion){
        return 0;
    }
    public int deleteQuestion(int questionId){
        return 0;
    }
    public int importQuestion(ArrayList<Question> questionList) {
        return 0;
    }

}
