/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author admin
 */
public class QuizLevel {
    private int quizLevelId;
    private String quizLevel;
    private boolean status;

    public QuizLevel() {
    }

    public QuizLevel(int quizLevelId, String quizLevel, boolean status) {
        this.quizLevelId = quizLevelId;
        this.quizLevel = quizLevel;
        this.status = status;
    }

    public int getQuizLevelId() {
        return quizLevelId;
    }

    public void setQuizLevelId(int quizLevelId) {
        this.quizLevelId = quizLevelId;
    }

    public String getQuizLevel() {
        return quizLevel;
    }

    public void setQuizLevel(String quizLevel) {
        this.quizLevel = quizLevel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
