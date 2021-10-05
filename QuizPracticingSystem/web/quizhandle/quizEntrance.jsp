<%-- 
    Document   : quizEntrance
    Created on : Oct 4, 2021, 2:45:40 PM
    Author     : ADMN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="http://localhost:8080/quizController?service=quizEntrance" method="POST">
            <input hidden name="quizId" value="3">
            <input type="submit" name="ticket" value="yes">
        </form>
    </body>
</html>
