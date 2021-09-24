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
public class QuizQuizHandle {

    private ArrayList<QuestionQuizHandle> questions;

    public QuizQuizHandle() {
        questions = new ArrayList<>();
    }

    public QuizQuizHandle(ArrayList<QuestionQuizHandle> questions) {
        this.questions = questions;
    }

    public ArrayList<QuestionQuizHandle> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionQuizHandle> questions) {
        this.questions = questions;
    }
    public QuestionQuizHandle getQuestionByNumber(int questionNumber){
        return questions.get(questionNumber-1);
    }

    public void addQuestion(QuestionQuizHandle question) {
        questions.add(question);
    }

    public void removeQuestion(QuestionQuizHandle question) {
        questions.remove(question);
    }

}
