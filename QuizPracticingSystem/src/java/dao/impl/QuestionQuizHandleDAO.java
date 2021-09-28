/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Answer;
import bean.Question;
import bean.QuestionQuizHandle;
import dao.MyDAO;
import dao.QuestionQuizHandleINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMN
 */
public class QuestionQuizHandleDAO extends MyDAO implements QuestionQuizHandleINT {

    @Override
    //Turn a Question into QuestionQuizHandle
    public QuestionQuizHandle generateQuestionById(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        Question question = questionDAO.getQuestionById(questionId);                        //get question
        ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(questionId);          //get question's answer list
        return new QuestionQuizHandle(question, answers, 0, false);                         //constructor(question,question's answers list, user's answerd id,marked)
    }

    @Override
    //mark and unmark question
    public void markQuestion(QuestionQuizHandle question) {
        if (question.isMarked()) {
            question.setMarked(false);
        } else {
            question.setMarked(true);
        }
    }

    @Override
    //get the right answer of question (peek at answer)
    public Answer getRightAnswer(QuestionQuizHandle question) {
        ArrayList<Answer> answerList = question.getAnswerList();
        for (Answer answer : answerList) {
            if (answer.isIsCorrect()) {
                return answer;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Boolean> getMarkQuestionList(int quizTakeId) {
        ArrayList<Boolean> markQuestionList = new ArrayList();
        String sql = "SELECT * FROM [MarkQuestion] WHERE quizTakeId =" + quizTakeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                markQuestionList.add(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
        }
        return markQuestionList;
    }

    @Override
    public ArrayList<QuestionQuizHandle> getReviewQuestion(int quizTakeId) {
        QuestionQuizHandleDAO questionQuizHandleDAO = new QuestionQuizHandleDAO();
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        ArrayList<QuestionQuizHandle> questionList = new ArrayList();
       
        String sql = "SELECT * FROM [TakeAnswer] WHERE quizTakeId=" + quizTakeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Question question = questionDAO.getQuestionById(rs.getInt("questionId"));
                ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(rs.getInt("questionId"));
                questionList.add(new QuestionQuizHandle(question,
                        answers,
                        rs.getInt("answerId"),
                        false));
              
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        
        return questionList;
    }

    public static void main(String[] args) {
        QuestionQuizHandleDAO dao = new QuestionQuizHandleDAO();
        QuestionDAO qdao = new QuestionDAO();
        ArrayList<Boolean> s = dao.getMarkQuestionList(11);
        for (boolean a : s) {
            System.out.println(a);
        }
    }
}
