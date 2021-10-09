/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
    private Timestamp submitedAt;
    private boolean status;
    private String quizName;
    private String subjectName;
    private String testTypeName;

    public CustomerQuiz() {
    }

    public CustomerQuiz(int quizTakeId, int quizId, int userId, int score, int time, Timestamp submitedAt, boolean status) {
        this.quizTakeId = quizTakeId;
        this.quizId = quizId;
        this.userId = userId;
        this.score = score;
        this.time = time;
        this.submitedAt = submitedAt;
        this.status = status;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public String getQuizName() {
        return quizName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public Timestamp getSubmitedAt() {
        return submitedAt;
    }

    public void setSubmitedAt(Timestamp submitedAt) {
        this.submitedAt = submitedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDateTaken() {
        Date date = new Date(submitedAt.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateTaken = simpleDateFormat.format(date);
        return dateTaken;
    }
    
    public String getDurationString(){
        String durationString = "";
        int minute = time / 60;
        int second = time % 60;
        durationString = minute + ":" + second;
        return durationString;
    }
}
