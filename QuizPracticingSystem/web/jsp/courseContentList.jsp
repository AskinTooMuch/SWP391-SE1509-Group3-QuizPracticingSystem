<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 29, 2021, 9:32:27 AM
    Display subject list in cards
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    29/9/21     1.0         ChucNVHE150618  First Deploy
    30/9/21     2.0         ChucNVHE150618  Add display list and include header, footer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <style>
            .subjectCard {
                margin: auto;
                width: 65vw;
                height: 20vh;
                border: 3px solid #73AD21;
                overflow: hidden;
                margin-top: 1vh;
                margin-bottom: 1vh;
            }

            .cardThumbnail {
                height: 100%;
                width: 20vh;
                display: inline-block;
            }

            .cardBody {
                display: inline-block;
                margin-left: 1vw;
            }

            .thumbNailImg{
                width: 100%;
                height: 100%;
            }
        </style>
    </head>
    <body>
        <div class="wrap">
            <%-- Include header page --%>
            <jsp:include page="header.jsp"/>
            <div class="main">
                <c:choose>
                    <%-- Case 1: subjectList is empty --%>
                    <c:when test = "${empty courseContentSubjectList}">
                        <div class="row" style="height: 50vh">
                            <h5 style="margin: auto; text-align: center">We currently don't have any course. 
                                <br> <a  href="">Add new course ? </a> </h5>
                        </div>
                    </c:when>
                    <%-- Case 2: subjectList is not empty --%>
                    <c:otherwise>
                        <div class="row" style="min-height: 50vh">
                            <%-- Print available subject --%>
                            <c:forEach items = "${courseContentSubjectList}" var="subject" begin = "0" end = "${courseContentSubjectList.size()-1}">
                                <div class="col-md-10 subjectCard">
                                    <%-- Image part of the card --%>
                                    <div class="cardThumbnail">
                                        <image class="thumbNailImg" src="${contextPath}/images/${subject.getThumbnail()}" alt="${subject.getThumbnail()}">
                                    </div>
                                    <%-- Body part of the card --%>
                                    <div class="cardBody">
                                        <h5><a href="subjectController?service=courseContentDetail&subjectId=${subject.getSubjectId()}">${subject.getSubjectName()}</a></h5>
                                        <p style="overflow: hidden">${subject.getDescription()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                            <%-- Print Subject placeholder card, same as subject card but the content is default --%>
                            <div class="col-md-10 subjectCard">
                                <div class="cardThumbnail">
                                    <image class="thumbNailImg" src="${contextPath}/images/logo.png" alt="logo.png">
                                </div>
                                <div class="cardBody">
                                    <a href="#"><h5> Add new course. </h5></a>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <%-- Include footer page --%>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>

</html>
