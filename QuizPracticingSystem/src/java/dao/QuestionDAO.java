/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Question;
import java.util.ArrayList;
import dao.impl.MyDAO;

/**
 *
 * @author admin
 */
public class QuestionDAO extends MyDAO {

    public ArrayList<Question> getQuestion(String content, int subjectId, int lessonId, boolean status) {
        return null;
    }

    public Question getQuestionById(int id) {
        return null;
    }

    public int addQuestion(Question a) {
        return 0;
    }
    public int editQuestion(Question a,boolean delete){
        return 0;
    }
    public int importQuestion(ArrayList<Question> list) {
        return 0;
    }

}
