<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Sep 26, 2021, 9:33:11 PM
    Display subject list in cards
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    26/9/21     1.0         ChucNVHE150618  First Deploy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subject List</title>
        <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <script src="${contextPath}/js/bootstrap.min.js"></script>
        <link href="${contextPath}/css/font-awesome.css" rel="stylesheet">
        <link href="${contextPath}/css/font-awesome.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="${contextPath}/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="wrap">
            <jsp:include page="Header.jsp"/>
            <div class="main">
                <c:choose>
                    <%-- Case 1: subjectList is empty --%>
                    <c:when test = "${empty subjectList}">
                        <div class="row" style="height: 50vh">
                            <h5 style="margin: auto; text-align: center">We currently don't have any course. 
                                <br> Feels Knowledgeable? Contact us and add your own course!</h5>
                        </div>
                    </c:when>
                    <%-- Case 2: subjectList is not empty --%>
                    <c:otherwise>
                        <div class="row" style="min-height: 50vh">
                            <%-- Print available subject --%>
                            <c:forEach items = "${subjectList}" var="subject" begin = "0" end = "${subjectList.size()-1}">
                                <div class="col-md-10 subjectCard">
                                    <div class="cardThumbnail">
                                        <image class="thumbNailImg" src="${contextPath}/images/${subject.getThumbnail()}" alt="${subject.getThumbnail()}">
                                    </div>
                                    <div class="cardBody">
                                        <h5>${subject.getSubjectName()}</h5>
                                        <p style="overflow: hidden">${subject.getDescription()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                            <%-- Print Subject placeholder card --%>
                            <div class="col-md-10 subjectCard">
                                <div class="cardThumbnail">
                                    <image class="thumbNailImg" src="${contextPath}/images/logo.png" alt="logo.png">
                                </div>
                                <div class="cardBody">
                                    <h5>Currently Not Available</h5>
                                    <p style="overflow: hidden">Feels Knowledgeable? Contact us and add your own course!</p>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
    <style>
        .subjectCard {
            margin: auto;
            width: 80vw;
            height: 20vh;
            border: 3px solid #73AD21;
            padding: 10px;
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
        }
        
        .thumbNailImg{
            width: 100%;
            height: 100%;
        }
    </style>
</html>
