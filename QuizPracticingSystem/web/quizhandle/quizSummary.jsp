<!--
   Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
   Created on : Sep 23, 2021
   QuizController map
   Quiz practicing system
 
   Record of change:
   Date        Version     Author          Description
   29/9/21     1.0         NamDHHE150519   First Deploy
   29/9/21     2.0         NamDHHE150519   complete all funtion
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Summary</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/quizhandle.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="infomation" >
                <div class='info row' style='display:flex;'>
                    <div class='col-1'>
                        <a type="button" class="btn" href="quizController?service=quizHandle&quizId=${quizId}&questionNumber=1" style='border:1px solid #4472c4; color:#4472c4;
                           margin-left: 5px;'> Back</a>
                    </div>
                    <div class='col-11'>
                        thong tin bai quiz
                    </div>
                </div>
            </div>
            <div class="row infomation1">
                thong tin bai quiz
            </div>
            <div class="container">
                <div class="row">
                    <div class="mainContent col-9" style="border-right: 1px solid black;">
                        <c:forEach items="${requestScope.quizSummary}" var="question">
                            <a href="quizController?service=quizHandle&quizId=${quizId}&questionNumber=${quizSummary.indexOf(question)+1}" class="btn allquestions ${question.getAnsweredId()!=0?"btn-secondary answered":"btn btn-light unanswered"}${question.isMarked()==true?" marked":" unmarked"} btn-lg active" id="${question.isMarked()==true?"marked":"unmarked"}" role="button">${quizSummary.indexOf(question)+1}</a>
                        </c:forEach>   
                    </div>
                    <div class="col-3" style="display:flex;">
                        <div style='margin-top:1px;'>
                            <h4>Your time:&nbsp;</h4>
                        </div>
                        <div class="detail1">
                            <img id="clockImage" src="images/timer.png"> <label for="clockImage">
                                <h3><label id="hours">--</label>:<label id="minutes">--</label>:<label id="seconds">--</label></h3>
                            </label>
                        </div>
                    </div>
                </div>
                <div class='row'>
                    <div class='col-12'style="display:grid;">
                        <button type="button" class="btn scorereview" data-toggle="modal" data-target=".submitthis" style="background-color: #4472c4;border: 1px white solid; color:white;">Score Exam</button>  
                    </div>
                </div>        
            </div>
            <div class="modal fade submitthis" id="submitModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <% int quizSize = (int) request.getAttribute("quizSize");%>
                            <% int answeredNumber = (int) request.getAttribute("answeredNumber");%>
                            <%if ((answeredNumber == quizSize) || (answeredNumber < quizSize && answeredNumber != 0)) {  %>   
                            <h5 class="modal-title" id="exampleModalLabel" >Score Exam?</h5>
                            <%}%>
                            <%if (answeredNumber == 0) { %>
                            <h5 class="modal-title" id="exampleModalLabel">Exit Exam?</h5>
                            <% } %>
                        </div>
                        <div class="modal-body">
                            <% if (answeredNumber == quizSize) { %>
                            By clicking on the [Score Exam] button below, you will complete your current exam and receive your score. You will not 
                            be able to change any answers after this point
                            <%}%>
                            <% if (answeredNumber < quizSize && answeredNumber > 0) {%>
                            <div style="display: flex;">
                                <p style="color:red;" id="numberOfAnswer"><%=answeredNumber%></p><p style="color:red;">&nbsp;of <%=quizSize%> Questions Answered</p>  
                            </div>
                            By clicking on the [Score Exam] button below, you will complete your current exam and receive your score. You will not 
                            be able to change any answers after this point
                            <% } %>
                            <% if (answeredNumber == 0) {%>
                            You have not answered any question.By clicking on the [Score Exam] button below, you will complete your current exam and receive your score. You will not 
                            be able to change any answers after this point
                            <% }%>
                        </div>
                        <form id='questionForm' action='quizController' method="POST">
                            <input hidden id="time" name="time">  
                            <input hidden name="service" value="submit">
                        </form>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Back</button>
                            <c:choose>
                                <c:when test="${answeredNumber ==0}">
                                    <input onclick="resetTime()" id="reviewSubmit" style="border:1px solid white; color:white; background-color: #4472c4;" class="btn" type="submit" name="action" value="Exit" form="questionForm">
                                </c:when>
                                <c:otherwise>

                                    <input onclick="resetTime()" id="reviewSubmit" style="border:1px solid white; color:white; background-color: #4472c4;" class="btn" type="submit" name="action" value="Score Exam" form="questionForm">
                                </c:otherwise>
                            </c:choose>                         
                        </div>
                    </div>
                </div>
            </div>
            <style>
                #marked{
                    border: 3px solid red;
                }
            </style>
        </div>
    </body>
    <script>
        var minutesLabel = document.getElementById("minutes");
        var secondsLabel = document.getElementById("seconds");
        var hoursLabel = document.getElementById("hours");
        var totalSecond;
        var today = new Date();
        <c:choose>
            <c:when test="${quizType==1}">
        var endMilisecond = localStorage.getItem("endMiliseconds");
        localStorage.setItem('endMiliseconds', endMilisecond);
        setInterval(setTime, 100);
        function setTime() {
            var today2 = new Date();
            var presentMilisecond = today2.getTime();
            totalSecond = (endMilisecond - presentMilisecond) / 1000;
            displayTime();
            
        }
        setInterval(autoSubmit, 600);
        function autoSubmit() {
            
            if (totalSecond < 0) {
                resetTime();
                document.getElementById("questionForm").submit();
            }
        }
            </c:when>
            <c:otherwise>
        var startMilisecond = localStorage.getItem("startMiliseconds");
        localStorage.setItem('startMiliseconds', startMilisecond);
        setInterval(setTime, 100);
        function setTime() {
            var today2 = new Date();
            var presentMilisecond = today2.getTime();
            totalSecond = (presentMilisecond - startMilisecond) / 1000;
            displayTime();
            
        }
        setInterval(autoSubmit, 600);
        function autoSubmit() {
            
            if (totalSecond > 15) {
                resetTime();
                document.getElementById('questionForm').submit();
            }
        }
            </c:otherwise>
        </c:choose>

        function displayTime() {
            var totalMinute = (totalSecond / 60) % 60;
            var totalHour = totalSecond / 60 / 60;
            secondsLabel.innerHTML = pad(parseInt(totalSecond % 60));
            minutesLabel.innerHTML = pad(parseInt(totalMinute));
            hoursLabel.innerHTML = pad(parseInt(totalHour));
            document.getElementById("time").value = Math.round(totalSecond);
        }
        function resetTime() {
            localStorage.clear();
        }

        function pad(val) {
            var valString = val + "";
            if (valString.length < 2) {
                return "0" + valString;
            } else {
                return valString;
            }
        }
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/9650a62e47.js" crossorigin="anonymous"></script>
</html>
