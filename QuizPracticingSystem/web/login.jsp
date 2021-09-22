
<%@page import="bean.User"%>
<%-- 
    Document   : login
    Created on : Sep 21, 2021, 10:25:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    
        <% String mess = (String) request.getAttribute("mess");
            if (mess != null) {
        %>
        <p> <%=mess%> </p>
        <%
            }
        %>
        
        <form action="userController" method="POST">
            <p>User Mail:</p>
            <input type="text" name="userMail">
            <p>Password:</p>
            <input type="text" name="password">
            <input type="hidden" name="service" value="login">
            <br><br>
            <input type="submit" value="Login">
        </form>
        
        <button><a href="userController?service=resetPassword"></a></button>
    
</html>

