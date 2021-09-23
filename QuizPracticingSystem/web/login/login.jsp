
<%@page import="bean.User"%>
<%-- 
    Document   : login
    Created on : Sep 21, 2021, 10:25:35 AM
    Author     : Admin
--%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <% String mess = (String) request.getAttribute("mess");
            if (mess != null) {
        %>
        <p> <%=mess%> </p>
        <%
            }
        %>
        
        <form action="../userController" method="POST">
            <p>User Mail:
            <input type="text" name="userMail" ></p>
            <p>Password:
            <input type="text" name="password"></p>
            <input type="hidden" name="service" value="login">
            <br><br>
            <button type="submit">Login</button>
        </form>
        
        <button><a href="userController?service=resetPassword"></a></button>
    </body>
</html>

