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
public class QuizQuizHandle {
    private Quiz quiz;
    private ArrayList<QuestionQuizHandle> questions;
    private User user;
    private int Time;   // thoi gian lam bai quiz tinh bang giay

    public QuizQuizHandle() {
        questions = new ArrayList<>();
    }

    public QuizQuizHandle(Quiz quiz, ArrayList<QuestionQuizHandle> questions, User user, int Time) {
        this.quiz = quiz;
        this.questions = questions;
        this.user = user;
        this.Time = Time;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
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
