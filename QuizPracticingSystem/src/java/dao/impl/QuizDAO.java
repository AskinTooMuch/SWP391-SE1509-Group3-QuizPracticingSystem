/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.DimensionType;
import bean.Question;
import bean.Quiz;
import bean.QuizLevel;
import bean.TestType;
import dao.MyDAO;
import dao.QuizINT;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ChucNVHE150618
 */
public class QuizDAO extends MyDAO implements QuizINT {

    @Override
    public ArrayList<Quiz> getAllQuiz() {
        ArrayList<Quiz> allQuiz = null;

        return allQuiz;
    }

    @Override
    public Quiz getQuizById(int quizId) {

        String sql = "SELECT * FROM [Quiz] WHERE quizId=" + quizId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                QuizLevelDAO quizLevelDAO = new QuizLevelDAO();
                QuizLevel quizLevel =quizLevelDAO.getQuizLevelById(rs.getInt("quizLevelId"));
                String quizLevelName  = quizLevel.getQuizLevelName();
                TestTypeDAO testTypeDAO = new TestTypeDAO();
                TestType testType = testTypeDAO.getTestTypeById(rs.getInt("testTypeId"));
                String testTypeName = testType.getTestTypeName();
                DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();
                DimensionType dimensionType = dimensionTypeDAO.getDimensionTypeById(rs.getInt("dimensionTypeId"));
                String dimensionTypeName = dimensionType.getDimensionTypeName();
                return new Quiz(rs.getInt("quizId"),
                        rs.getInt("lessonId"),
                        rs.getInt("subjectId"),
                        rs.getString("quizName"),
                        rs.getInt("quizLevelId"),
                        quizLevelName,
                        rs.getInt("quizDuration"),
                        rs.getInt("passRate"),
                        rs.getInt("testTypeId"),
                        testTypeName,
                        rs.getString("description"),
                        rs.getInt("numberQuestion"),
                        rs.getInt("dimensionTypeId"),
                        dimensionTypeName,
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    @Override
    public Quiz getQuizByQuizTakeId(int quizTakeId){
        String sql = "select * from Quiz as a join CustomerQuiz as b on a.quizId = b.quizId where quizTakeId="+quizTakeId;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                QuizLevelDAO quizLevelDAO = new QuizLevelDAO();
                QuizLevel quizLevel =quizLevelDAO.getQuizLevelById(rs.getInt("quizLevelId"));
                String quizLevelName  = quizLevel.getQuizLevelName();
                TestTypeDAO testTypeDAO = new TestTypeDAO();
                TestType testType = testTypeDAO.getTestTypeById(rs.getInt("testTypeId"));
                String testTypeName = testType.getTestTypeName();
                DimensionTypeDAO dimensionTypeDAO = new DimensionTypeDAO();
                DimensionType dimensionType = dimensionTypeDAO.getDimensionTypeById(rs.getInt("dimensionTypeId"));
                String dimensionTypeName = dimensionType.getDimensionTypeName();
                return new Quiz(rs.getInt("quizId"),
                        rs.getInt("lessonId"),
                        rs.getInt("subjectId"),
                        rs.getString("quizName"),
                        rs.getInt("quizLevelId"),
                        quizLevelName,
                        rs.getInt("quizDuration"),
                        rs.getInt("passRate"),
                        rs.getInt("testTypeId"),
                        testTypeName,
                        rs.getString("description"),
                        rs.getInt("numberQuestion"),
                        rs.getInt("dimensionTypeId"),
                        dimensionTypeName,
                        rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public ArrayList<Quiz> getQuizBySubject(int subjectId) {
        ArrayList<Quiz> subjectQuiz = null;

        return subjectQuiz;
    }

    @Override
    public ArrayList<Quiz> getQuizByLesson(int lessonId) {
        ArrayList<Quiz> lessonQuiz = null;

        return lessonQuiz;
    }

    @Override
    public int editQuiz(int quizId, Quiz quiz) {
        int i = 0;

        return i;
    }

    @Override
    public int addQuiz(Quiz quiz) {
        int i = 0;

        return i;
    }

    @Override
    public int deleteQuiz(int quizId) {
        int i = 0;

        return i;
    }
     public static void main(String[] args) {
        QuizDAO dao = new QuizDAO();
        Quiz quiz = dao.getQuizById(1);
        System.out.print(quiz.getQuizDuration());
    }

}
