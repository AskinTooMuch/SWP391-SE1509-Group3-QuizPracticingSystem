<%-- 
    Document   : register
    Created on : Sep 21, 2021, 10:25:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${mess != null}">
            <c:out value="${mess}"></c:out>
        </c:if>

        <form action="../userController" method="POST">
            <p>Username:</p>
            <input type="text" name="userName">
            <p>Password:</p>
            <input type="password" name="password">
            <p>Confirm your password:</p>
            <input type="password" name="confirmPass">
            <p>Email address:</p>
            <input type="text" name="userMail">
            <p>Phone number:</p>
            <input type="text" name="userMobile">
            <p>Gender:</p>
            <input type="radio" name="gender" value="Male"> Male
            <input type="radio" name="gender" value="Female"> Female
            <input type="hidden" name="service" value="register">
            <br>
            <input type="submit" value="Register">
        </form>

    </body>
</html>
