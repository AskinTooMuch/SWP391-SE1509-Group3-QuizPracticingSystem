<%-- 
    Document   : resetPass
    Created on : Sep 22, 2021, 8:51:19 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="userMail" value="${ param.userMail }"/>
        <c:choose >
            <c:when  test="${ userMail != null }">
                <c:set var="currentTime" value="${System.currentTimeMillis() }"/>
                <c:set var="createTime" value="${ param.createTime }"/>
                <c:choose>
                    <c:when test = "${ ((currentTim - createTime) / 1000 / 60) < 3 }">
                        <form action="${contextPath}/userController" method="post">
                            <p>Enter your new pass</p>
                            <input type="password" name="newPass">
                            <p>Re-Enter your new pass</p>
                            <input type="password" name="confirmNewPass">
                            <input type="hidden" name="service" value="resetPage">
                            <input type="hidden" name="userMail" value="<c:out value="${userMail}"></c:out>">
                                <input type="submit" value="Confirm">
                            </form>
                    </c:when>
                    <c:otherwise>
                        <p>Your link have over due</p>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <form action="${contextPath}/userController" method="post">
                    <input type="hidden" name="service" value="resetPassword">
                    <p>Enter your email</p>
                    <input type="text" name="userMail" >
                    <input type="submit" value="Send">
                </form>
            </c:otherwise>
        </c:choose>


        
    </body>
</html>
