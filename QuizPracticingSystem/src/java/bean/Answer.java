/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author duong
 */
public class Answer {
    private int answeId,questionId;
    private String answerContent;
    private boolean isCorrect,status;

    public Answer(int answeId, int questionId, String answerContent, boolean isCorrect, boolean status) {
        this.answeId = answeId;
        this.questionId = questionId;
        this.answerContent = answerContent;
        this.isCorrect = isCorrect;
        this.status = status;
    }

    public int getAnsweId() {
        return answeId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public boolean isStatus() {
        return status;
    }

    public void setAnsweId(int answeId) {
        this.answeId = answeId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
