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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <div class="header">
                <div class="container-fluid">
                    <div class="row" style="border-bottom: 1px black solid; border-top: 1px black solid;height:40px;margin-top: 30px; width: 103%;">
                        <div class="col-9">                 
                        </div>
                        <div class="col-3" style="display:contents; ">
                            <img style="height:25px;margin-top: 6px;" src="images/question.png"> <h3 style="margin-right:10px;">${questionNumber}/${quizSize}</h3>  <img src="images/timer.png" style="height:25px;margin-top: 6px;"> <h3><label id="hours">00</label>:<label id="minutes">00</label>:<label id="seconds">00</label></h3>
                        </div>  
                    </div>
                    <div class="row" style="background-color: buttonface; height:30px;width: 103%;">
                        <div class="col-9">
                            <h6 style='margin-top: 3px;'>${questionNumber})</h6>
                        </div>
                        <div class="col-3">
                            <h6 style='margin-top: 3px;margin-left: 110px;'>Question ID: ${questionId}</h6>
                        </div>
                    </div>
                </div>
            </div>      
            <style>
                ul li::marker {
                    font-weight: bold;
                }
            </style>
            <div class="mainContent">
                <div class="row" style='margin-left:50px;'>
                    ${questionContent}
                </div>
                <div class="row">
                    <form id='questionForm' action='quizhandle' method='POST'>
                        <ul>
                            <c:forEach items="${answerList}" var="answer">

                                <div class="checkbox-inline" style="display: -webkit-inline-box;">
                                    <input onclick="" type="checkbox" name="answertake" value="${answer.getAnswerId()}" id="answertake">
                                    <li  style='list-style: upper-alpha; margin-left: 50px;'>
                                        ${answer.getAnswerContent()}             
                                    </li>
                                </div>
                                <br/>
                            </c:forEach>
                        </ul>
                    </form>    
                </div>
            </div>
            <div class="button fixed-bottom" style="margin-bottom: 100px;margin-right: 30px;"> 
                <div class='row' style="float:right;">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-sm">Peek At Answer</button>
                    <form id="markForm" action="quizController?service=quizHandle&questionNumber=${questionNumber}" method="POST">
                        <button onclick="this.form.submit()">Mark For Review</button>
                        <input hidden name="marked" value="yes">
                    </form>
                </div>
            </div>
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
            <div class="funtionBar fixed-bottom" style='height:70px; background-color: #99ff99;'>
                <div style="margin-top:20px;">
                    <div style="float:right;">                    
                        <input type="submit" name='action' value='Previous' form='questionForm'>
                        <input type='submit' name='action' value='Next' form="questionForm">
                    </div>
                    <div >
                        <button style='margin-left: 10px;' type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-xl">Review Progress</button>                  
                    </div>
                </div>
            </div>
            <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">

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
                            <img src ='images/guide.png' style="width: 30%;height:auto;"> <button style='float:right;'>Score Exam Now</button><br/><br/>
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
            var totalSeconds = 0;
            var totalMinutes = 0;
            setInterval(setTime, 1000);

            function setTime() {
                ++totalSeconds;
                secondsLabel.innerHTML = pad(totalSeconds % 60);
                minutesLabel.innerHTML = pad(parseInt(totalSeconds / 60));



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

</html>
