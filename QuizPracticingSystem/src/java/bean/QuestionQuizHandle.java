/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 27, 2021
 *  Subject entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  27/9/21     1.0         NamDHHE150519   First Deploy
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
    private int answeredId;
    private boolean marked;

    public QuestionQuizHandle() {
    }

    public QuestionQuizHandle(Question question, ArrayList<Answer> answerList, int answeredId, boolean marked) {
        this.question = question;
        this.answerList = answerList;
        this.answeredId = answeredId;
        this.marked = marked;
    }

   

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getAnsweredId() {
        return answeredId;
    }

    public void setAnsweredId(int answeredId) {
        this.answeredId = answeredId;
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
