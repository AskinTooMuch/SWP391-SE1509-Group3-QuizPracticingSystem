/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import bean.CustomerQuiz;
import bean.Quiz;
import bean.QuizQuizHandle;
import dao.CustomerQuizDAO;
import dao.QuizDAO;
import dao.QuizQuizHandleDAO;
import dao.impl.CustomerQuizDAOImpl;
import dao.impl.QuizDAOImpl;
import dao.impl.QuizQuizHandleDAOImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author ADMN
 */
public class NewServletListener implements HttpSessionListener, HttpSessionBindingListener, HttpSessionAttributeListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("\n###################################\n");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        System.out.println("sessionCreated method has been called in "
                + this.getClass().getName());

        HttpSession session = se.getSession();

        System.out.print(session + " Created:");

        System.out.println("ID=" + session.getId() + " MaxInactiveInterval="
                + session.getMaxInactiveInterval() + "created: " + sdf.format(date.getTime()));

        System.out.println("\n#####################################\n");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Date date = new Date();
        System.out.println("\n###################################\n");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        System.out.println("sessionDestroyed method has been called in "
                + this.getClass().getName());

        HttpSession session = se.getSession();

        System.out.print(session + " destroyed:");

        System.out.println("ID=" + session.getId() + " MaxInactiveInterval="
                + session.getMaxInactiveInterval() + "created: " + sdf.format(date.getTime()));

        System.out.println("\n#####################################\n");

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("\n###################################\n");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        
        System.out.println("created: " + sdf.format(date.getTime()));
//        System.out.println("\n###################################\n");
        if (se.getName().equalsIgnoreCase("questionArray")) {
        QuizQuizHandle questionArray = (QuizQuizHandle) se.getValue();
        int timeOut = questionArray.getQuiz().getQuizDuration()+4;
        Time(104, se);
        }
    }
    Timer timer;

    public void Time(int seconds, HttpSessionBindingEvent se) {
        timer = new Timer();
        timer.schedule(new RemindTask(se), seconds * 1000); // schedule the task
    }

    class RemindTask extends TimerTask {

        private HttpSessionBindingEvent se;

        public RemindTask(HttpSessionBindingEvent se) {
            this.se = se;
        }

        @Override
        public void run() {
            se.getSession().removeAttribute("questionArray");
            System.out.println("Quiz Expired!");
            timer.cancel(); //Terminate the timer thread
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("-- HttpSessionAttributeListener#attributeRemoved() --");
        System.out.printf("removed: " + sdf.format(date.getTime()) + "removed attribute name: %s, value:%s %n", se.getName(),
                se.getValue());
        if (se.getName().equalsIgnoreCase("questionArray")) {
            try {
                QuizQuizHandle questionArray = (QuizQuizHandle) se.getValue();
                QuizQuizHandleDAO quizQHInterface = new QuizQuizHandleDAOImpl();
                int quizId = questionArray.getQuiz().getQuizId();
                //Score of this quiz    
                double score = quizQHInterface.calculateScore(questionArray);
                //Date of this quiz
                int time = questionArray.getTime();
                if(time<0){
                    time=0;
                }
                long millis = System.currentTimeMillis();
                java.sql.Date dateSql = new java.sql.Date(millis);
                //Insert into CustomerQuiz table in database
                CustomerQuiz customerQuiz = new CustomerQuiz(0, quizId, 2, (int) score,time, dateSql, true);
                CustomerQuizDAO customerQuizInterface = new CustomerQuizDAOImpl();
                customerQuizInterface.addCustomerQuiz(customerQuiz);
                //Insert into TakeAnswer table in database;
                customerQuizInterface.addTakeAnswer(questionArray);
                //Inser into MarkQuestion table in database;
                customerQuizInterface.addMarkQuestion(questionArray);
            } catch (Exception e) {

            }
        }
    }
}
