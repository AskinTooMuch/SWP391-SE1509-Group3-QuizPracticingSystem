/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Timestamp;

/**
 *
 * @author ChucNVHE150618
 */
public class CustomerQuiz {
    private int quizTakeId;
    private int quizId;
    private int userId;
    private int score;
    private int time;
    private Timestamp startedAt;
    private boolean status;

    public CustomerQuiz() {
    }

    public CustomerQuiz(int quizTakeId, int quizId, int userId, int score, int time, Timestamp startedAt, boolean status) {
        this.quizTakeId = quizTakeId;
        this.quizId = quizId;
        this.userId = userId;
        this.score = score;
        this.time = time;
        this.startedAt = startedAt;
        this.status = status;
    }

  

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    
    public int getQuizTakeId() {
        return quizTakeId;
    }

    public void setQuizTakeId(int quizTakeId) {
        this.quizTakeId = quizTakeId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

   

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    } 
}
