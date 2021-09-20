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
public class Question {

    private int questionId;
    private int subjectId;
    private int dimensionId;
    private int lessonId;
    private String content;
    private String media;
    private String explanation;
    private boolean status;

    public Question() {
    }

    public Question(int questionId, int subjectId, int dimensionId, int lessonId, String content, String media, String explanation, boolean status) {
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.dimensionId = dimensionId;
        this.lessonId = lessonId;
        this.content = content;
        this.media = media;
        this.explanation = explanation;
        this.status = status;
    }

    

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
