<%-- 
    Document   : editProfile
    Created on : Sep 26, 2021, 2:41:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrapp.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-xs-1"></div>
        <div class="col-xs-10">
            <c:if test="${currUser == null}">
                <c:redirect url="/login/login.jsp"></c:redirect>
            </c:if>
            <c:if test="${mess != null}">
                <c:out value="${mess}"></c:out>
            </c:if>
            <form action="${contextPath}/userController?service=test" enctype="multipart/form-data">
                
                <p>Profile Picture </p>
                <input type="file" name="profilePic">
                <br>
                <input type="submit" value="Register">
            </form>
        </div>
        <div class="col-xs-1"></div>
    </body>
</html>
