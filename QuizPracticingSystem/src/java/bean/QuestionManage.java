/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author tuan
 */
public class QuestionManage {
    private int questionId;
    private String subjectName;
    private String dimensionName;
    private String lessonName;
    private String content;
    private String media;
    private String explanation;
    private boolean status;

    public QuestionManage() {
    }

    public QuestionManage(int questionId, String subjectName, String dimensionName, String lessonName, String content, String media, String explanation, boolean status) {
        this.questionId = questionId;
        this.subjectName = subjectName;
        this.dimensionName = dimensionName;
        this.lessonName = lessonName;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
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
