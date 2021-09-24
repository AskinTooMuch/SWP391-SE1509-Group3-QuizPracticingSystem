/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public class QuestionQuizHandle {

    private Question question;
    private ArrayList<Answer> answerList;
    private boolean checked;

    public QuestionQuizHandle() {
    }

    public QuestionQuizHandle(Question question, ArrayList<Answer> answerList, boolean checked) {
        this.question = question;
        this.answerList = answerList;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }

}
