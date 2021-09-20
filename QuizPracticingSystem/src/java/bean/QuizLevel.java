/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizLevel {
    private int quizLevelId;
    private String quizLevelName;
    private boolean status;

    public QuizLevel() {
    }

    public QuizLevel(int quizLevelId, String quizLevelName, boolean status) {
        this.quizLevelId = quizLevelId;
        this.quizLevelName = quizLevelName;
        this.status = status;
    }

    public int getQuizLevelId() {
        return quizLevelId;
    }

    public void setQuizLevelId(int quizLevelId) {
        this.quizLevelId = quizLevelId;
    }

    public String getQuizLevel() {
        return quizLevelName;
    }

    public void setQuizLevel(String quizLevel) {
        this.quizLevelName = quizLevel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
