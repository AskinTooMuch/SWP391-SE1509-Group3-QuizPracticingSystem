/*
  Lớp này có các phương thức thực hiện tạo ra những câu hỏi trong bài quiz bằng 
  các câu hỏi lấy từ database, kết hợp với QuizQuizHandle để
  phục vụ funtion QuizHandle hoặc QuizReview
  @author Đinh Hải Nam
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
    /*
    *Turn a Question into QuestionQuizHandle.
    *
    *@param question the question. It is a Question type
    *@param answers the list of question's answers. It is ArrayList<Question> type
    */
    public QuestionQuizHandle generateQuestionById(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        Question question = questionDAO.getQuestionById(questionId);                        //get question
        ArrayList<Answer> answers = answerDAO.getAnswersByQuenstionId(questionId);          //get question's answer list
        return new QuestionQuizHandle(question, answers, 0, false);                         //constructor(question,question's answers list, user's answerd id,marked)
    }

    @Override
//    mark and unmark question
//    
    public void markQuestion(QuestionQuizHandle question) {
        if (question.isMarked()) {
            question.setMarked(false);
        } else {
            question.setMarked(true);
        }
    }

    @Override
    /*
    *Get the right answer of the question 
    *
    *@param answer the answer of the question. It is a Answer type
    *@param answerList the list of question's answers. It is ArrayList<Question> type
    */
    public Answer getRightAnswer(QuestionQuizHandle question) {
        ArrayList<Answer> answerList = question.getAnswerList();
        for (Answer answer : answerList) {
            if (answer.isIsCorrect()) {
                return answer;
            }
        }
        return null;
    }
    /*
    *Get the list of question marked from the QuizTake table in database 
    *
    *@param pre. It is a PreparedStatement type
    *@param rs. It is ResultSet type
    */
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
