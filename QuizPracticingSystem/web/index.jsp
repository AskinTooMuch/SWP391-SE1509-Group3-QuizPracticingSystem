<%-- 
    Document   : Index
    Created on : Sep 21, 2021, 10:36:55 AM
    Author     : tuan
--%>

<%@page import="bean.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/bootstrap.min.js"></script>
        <link href="css/font-awesome.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        
        <c:if test = "${subjectList == null}">
            <c:redirect url="homeController"/>
        </c:if>
        <div class="wrap">
            <jsp:include page="jsp/Header.jsp"/>
            <div class="main">
                <jsp:include page="jsp/Menu.jsp"/>
            </div>
            <jsp:include page="jsp/Footer.jsp"/>
        </div>
    </body>
</html>
