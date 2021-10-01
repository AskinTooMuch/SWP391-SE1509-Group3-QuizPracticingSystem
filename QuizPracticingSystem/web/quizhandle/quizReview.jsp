<!--
   Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
   Created on : Sep 23, 2021
   QuizController map
   Quiz practicing system
 
   Record of change:
   Date        Version     Author          Description
   23/9/21     1.0         NamDHHE150519   First Deploy
   24/9/21     1.1         NamDHHE150519   Update
   25/9/21     1.2         NamDHHE150519   Big Update
   29/9/21     1.9         NamDDHE150519   complete all funtion
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Review</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/quizhandle.css">
    </head>
    <body>

        <div class="container-fluid">
            <!--start header-->
            <div class="infomation">
                <div class="info row" style="">
                    <div col-1>
                        ${requestScope.score}
                        <button class="goBack" type="button" class="btn" style=""> Go Back</button>
                    </div>
                    <div class="col-11">    
                        <div class="detail">
                            <div class="detail1">
                                <img id="questionImage" src="images/question.png"> <label for="questionImage">
                                    <h3 style="">${questionNumber}/${quizSize}</h3> </label>
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
                <div class="row answers" style="margin-top:10px;">

                    <div class="col-12">
                        <ul style='margin-left:50px;margin-top: 10px;'>                  
                            <c:forEach items="${answerList}" var="answer">
                                <c:if test="${answer.getAnswerId()==answered}">
                                    <img src="images/youranswer.png" style='width:90px;height: auto;'>
                                </c:if>

                                <div class="checkbox-inline ${answer.getAnswerId()==answered?"":"wrong"}" style="display: -webkit-inline-box;">
                                    <label class="labelA" for="${answer.getAnswerId()}">
                                        <li>
                                            ${answer.getAnswerContent()} 
                                        </li>
                                        <input type="radio" name="answerTakenId" value="${answer.getAnswerId()}" id="${answer.getAnswerId()}" ${answer.getAnswerId()==answered?"checked":"disabled"} id="answertake">

                                        <span class="checkmark ${answer.getAnswerId()==answered?"":"disable"}"></span>
                                    </label>
                                </div>
                                <c:if test="${answer.isIsCorrect()}">
                                    <img src="images/right.png" style='width:20px;height: auto;'>
                                </c:if>
                                <c:if test="${!answer.isIsCorrect()}">
                                    <img src="images/wrong.png" style='width:20px;height: auto;'>
                                </c:if>

                                <br/>
                            </c:forEach>
                        </ul>
                    </div>
                    <style>
                        .wrong{
                            margin-left: 94.2px;
                        }
                    </style>
                    <div class="col-1"></div>
                </div>
            </div>
            <!--                           end mainContent-->
            <!--                                       funtion bar-->
            <div class="modal fade eplain" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Explanation</h5>                           
                        </div>
                        <div class="modal-body">
                            Explanation: ${explanation}
                        </div>
                        <div class="modal-footer">
                            Source
                        </div>
                    </div>
                </div>
            </div>
            <div class="funtionBar fixed-bottom" style='height:70px; background-color: #4472c4;'>
                <div style="margin-top:20px;margin-right: 20px;">
                    <div style="float:right;">
                        <button type="button" class="btn" data-toggle="modal" data-target=".eplain" >Explanation</button>
                        <c:if test="${questionNumber!=1}">
                            <a class="btn" href="quizController?service=quizReview&quizTakeId=${quizTakeId}&questionNumber=${questionNumber-1}">Previous Question</a>
                        </c:if>
                        <c:if test="${questionNumber!=quizSize}">
                            <a class="btn" href="quizController?service=quizReview&quizTakeId=${quizTakeId}&questionNumber=${questionNumber+1}">Next Question</a>
                        </c:if>
                        <c:if test="${questionNumber==quizSize}">
                            <button type="button" class="btn" data-toggle="modal" data-target=".submit" >Finish Review</button>
                        </c:if>
                    </div>
                    <div >
                        <button  type="button" class="btn" data-toggle="modal" data-target=".bd-example-modal-xl">Review Results</button>                      
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
                                <h5 class="modal-title" id="exampleModalLabel">Review Results</h5>
                                <br/>
                                <h7>Review your quiz results</h7>
                            </div>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>

                        </div>
                        <div class="modal-body">
                            <div>
                                <input type="image" id="unansweredbutton"  src="images/unanswered.png" alt="Submit Form" />
                                <input type="image" id="markedbutton" src="images/marked.png " alt="Submit Form" />
                                <input type="image" id="answeredbutton" src="images/answered.png" alt="Submit Form" />
                                <input type="image" id="allquestionsbutton" src="images/allquestions.png" alt="Submit Form" />

                                <button type="button" class="btn scorereview" data-toggle="modal" data-target=".submit" style="float:right;background-color: #4472c4;border: 1px white solid; color:white;">Finish Review</button>
                            </div>
                            <br/><br/>
                            <div class="holder" style='margin-left:20px'>
                                <c:forEach items="${requestScope.quizReview}" var="question">
                                    <a href="quizController?service=quizReview&quizTakeId=${quizTakeId}&questionNumber=${quizReview.indexOf(question)+1}" class="btn allquestions ${question.getAnsweredId()!=0?"btn-secondary answered":"btn btn-light unanswered"}${question.isMarked()==true?" marked":" unmarked"} btn-lg active" id="${question.isMarked()==true?"marked":"unmarked"}" role="button">${quizReview.indexOf(question)+1}</a>
                                </c:forEach>                             
                            </div>
                            <style>
                                #marked{
                                    border:red 3px solid;                                  
                                }
                                #reviewSubmit{
                                    background-color: #4472c4;border: 1px white solid; color:white;
                                }
                            </style>
                        </div>
                    </div>
                </div>
            </div>
            <!--            score exam modal-->
            <div class="modal fade submit" id="submitModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">                   
                            <h5 class="modal-title" id="exampleModalLabel">Finish Review?</h5>        
                        </div>
                        <div class="modal-body">
                            By clicking on the [Finish Review] button below, you will complete your current review. You will
                            be taking back to the customer quiz screen
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Back</button>
                            <a href="homeController" id="reviewSubmit" class="btn">Finish Review</a>                              
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            const unanswered = document.getElementById('unansweredbutton');
            const marked = document.getElementById('markedbutton');
            const answered = document.getElementById('answeredbutton');
            const allquestions = document.getElementById('allquestionsbutton');
            unanswered.addEventListener("click", () => {
                var y = document.getElementsByClassName("allquestions");
                var i;
                for (i = 0; i < y.length; i++) {
                    y[i].style.display = 'inline-flex';
                }
                var x = document.getElementsByClassName("answered");
                var i;
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = 'none';
                }
            });
            marked.addEventListener("click", () => {
                var y = document.getElementsByClassName("allquestions");
                var i;
                for (i = 0; i < y.length; i++) {
                    y[i].style.display = 'inline-flex';
                }
                var x = document.getElementsByClassName("unmarked");
                var i;
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = 'none';
                }
            });
            answered.addEventListener("click", () => {
                var y = document.getElementsByClassName("allquestions");
                var i;
                for (i = 0; i < y.length; i++) {
                    y[i].style.display = 'inline-flex';
                }
                var x = document.getElementsByClassName("unanswered");
                var i;
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = 'none';
                }
            });
            allquestions.addEventListener("click", () => {
                var y = document.getElementsByClassName("allquestions");
                var i;
                for (i = 0; i < y.length; i++) {
                    y[i].style.display = 'inline-flex';
                }
            });
        </script>


    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/9650a62e47.js" crossorigin="anonymous"></script>
</html>
