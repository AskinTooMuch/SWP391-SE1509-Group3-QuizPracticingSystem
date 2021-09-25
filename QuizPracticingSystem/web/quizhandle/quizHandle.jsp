<%-- 
    Document   : quizHandle
    Created on : Sep 24, 2021, 8:51:12 PM
    Author     : ADMN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Handle</title>
           <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/quizhandle.css">
    </head>
    <body>
        <jsp:include page="/jsp/Header.jsp"></jsp:include>
            <div class="container-fluid">
                <!--start header-->
                <div class="infomation">
                    <div class="info row" style="">

                        <div class="col-12">    
                            <div class="detail">
                                <div class="detail1">
                                    <img id="questionImage" src="images/question.png"> <label for="questionImage">
                                        <h3 style="">${questionNumber}/${quizSize}</h3> </label>
                            </div>
                            <div class="detail1">
                                <img id="clockImage" src="images/timer.png"> <label for="clockImage">
                                    <h3><label id="hours">--</label>:<label id="minutes">--</label>:<label id="seconds">--</label></h3>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row infomation1">
                <div class="col-1">
                    <h6 >${questionNumber})</h6>
                </div>
                <div class="col-11">
                    <h6 style=float:right;'>Question ID: ${questionId}</h6>
                </div>
            </div>
            <!--end header-->
            <style>
                ul li::marker {
                    font-weight: bold;
                }
            </style>
            <div class="mainContent">
                <div class="row question">
                    <div class="col-1"></div>
                    <div class="col-11">
                        <h4>${questionContent}</h4>
                    </div>
                </div>
                <c:set var="answered" value="${requestScope.answered}"/>
                <div class="row answers">
                    <div class="col-1"></div>
                    <div class="col-11">
                        <form id='questionForm' action='quizController?service=quizHandle&questionNumber=${questionNumber}' method='POST'>
                            <ul>
                                <input hidden name="questionTakenNumber" value="${questionNumber}">
                                <c:forEach items="${answerList}" var="answer">

                                    <div class="checkbox-inline" style="display: -webkit-inline-box;">


                                        <label class="labelA" for="${answer.getAnswerId()}">
                                            <li>
                                                ${answer.getAnswerContent()} 
                                            </li>
                                            <input onclick="" type="radio" name="answerTakenId" value="${answer.getAnswerId()}" id="${answer.getAnswerId()}" ${answer.getAnswerId()==answered?"checked":""} id="answertake">

                                            <span class="checkmark"></span>
                                        </label>

                                    </div>
                                    <br/>
                                </c:forEach>
                            </ul>
                        </form>    
                    </div>
                    
                    <div class="col-1"></div>
                </div>
            </div>
            <!--                           end mainContent-->
            <!--            peek and mark question-->

            <div class="funtion fixed-bottom" style="margin-bottom: 100px;margin-right: 30px;"> 
                <div class='row'>
                    <div class="col-9">                   
                    </div>
                    <div class="col-3">
                        <div style="float:right; display:flex;">
                            <button style="margin-right: 3px; border:1px solid black; background-color: white; color: black;" type="button" class="btn" data-toggle="modal" data-target=".bd-example-modal-sm">Peek At Answer</button>
                            <form id="markForm" action="quizController?service=quizHandle&questionNumber=${questionNumber}" method="POST">
                                <button class="btn " onclick="this.form.submit()">Mark For Review</button>
                                <input hidden name="marked" value="yes">
                            </form>
                        </div>                       
                    </div>
                </div>
            </div>
            <!--                        peek modal-->
            <div class="modal fade bd-example-modal-sm" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Peek At Answer</h5>                           
                        </div>
                        <div class="modal-body">
                            The correct Answer is: ${trueAnswer}<br/><br/>
                            Explanation: ${explanation}
                        </div>
                        <div class="modal-footer">
                            Source
                        </div>
                    </div>
                </div>
            </div>
            <!--                        end peek modal-->
            <div class="funtionBar fixed-bottom" style='height:70px; background-color: #4472c4;'>
                <div style="margin-top:20px;margin-right: 20px;">
                    <div style="float:right;">                    
                        <c:if test="${questionNumber!=1}">
                            <input class="btn" type="submit" name='action' value='Previous Question' form='questionForm'>
                        </c:if>
                        <c:if test="${questionNumber!=quizSize}">
                            <input class="btn" type='submit' name='action' value='Next Question' form="questionForm">
                        </c:if>
                        <c:if test="${questionNumber==quizSize}">
                            <input class="btn" type='submit' name='action' value='Score Exam' form="questionForm">
                        </c:if>
                    </div>
                    <div >
                        <button  type="button" class="btn" data-toggle="modal" data-target=".bd-example-modal-xl">Review Progress</button>                  
                    </div>
                </div>
            </div>
            <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
                <style>

                </style>
                <div class="modal-dialog modal-xl">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div>
                                <h5 class="modal-title" id="exampleModalLabel">Review Progress</h5>
                                <br/>
                                <h7>Review before score</h7>
                            </div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>

                        </div>
                        <div class="modal-body">
                            <img src ='images/guide.png' style="width: 30%;height:auto;">
                            <form id="quizsubmit"> 
                                <input hidden id="time" value="" name="time">
                                <button onclick="return submitConfirm()" style='float:right;'>Score Exam Now</button>
                            </form><br/><br/>
                            <div style='margin-left:20px'>
                                <c:forEach items="${requestScope.quiz}" var="question">
                                    <a href="#" class="btn ${question.getAnsweredId()!=0?"btn-secondary":"btn btn-light"} btn-lg active" id="${question.isMarked()==true?"marked":""}" role="button" aria-pressed="true">${quiz.indexOf(question)+1}</a>
                                </c:forEach>                             
                            </div>
                            <style>
                                #marked{
                                    border:red 1px solid;
                                }
                            </style>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var minutesLabel = document.getElementById("minutes");
            var secondsLabel = document.getElementById("seconds");
            var hoursLabel = document.getElementById("hours");
            var today = new Date();
            var startMilisecond;

            if (localStorage.getItem("miliSeconds") != null) {
                startMilisecond = localStorage.getItem("seconds");
            } else {
                startMilisecond = today.getTime();
            }
            localStorage.setItem('miliSeconds', startMilisecond);
            setInterval(setTime, 100);
            function setTime() {
                var today2 = new Date();
                var presentMilisecond = today2.getTime();
                var totalSecond = (presentMilisecond - startMilisecond) / 1000;
                var totalMinute = (totalSecond / 60) % 60;
                var totalHour = totalSecond / 60 / 60;
                secondsLabel.innerHTML = pad(parseInt(totalSecond % 60));
                minutesLabel.innerHTML = pad(parseInt(totalMinute));
                hoursLabel.innerHTML = pad(parseInt(totalHour));
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


    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/9650a62e47.js" crossorigin="anonymous"></script>
</html>
