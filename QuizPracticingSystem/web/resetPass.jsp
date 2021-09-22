<%-- 
    Document   : resetPass
    Created on : Sep 22, 2021, 8:51:19 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String userMail = (String) request.getParameter("userMail");
            Long createTime = Long.parseLong((String) request.getParameter("createTime"));
            Long currentTime = System.currentTimeMillis();
            long timeDiff = (currentTime - createTime) / 1000 / 60;
            if (timeDiff < 3) {
        %>
        <form action="userController" method="post">
            <p>Enter your new pass</p>
            <input type="password" name="newPass">
            <p>Re-Enter your new pass</p>
            <input type="password" name="confirmNewPass">
            <input type="hidden" name="service" value="resetPage">
            <input type="hidden" name="userMail" value="<%=userMail%>">
            <input type="submit" value="Confirm">
        </form>>
        <%
        } else {
        %>
        <p>Your link have over due</p>
        <%
            }
        %>
    </body>
</html>
