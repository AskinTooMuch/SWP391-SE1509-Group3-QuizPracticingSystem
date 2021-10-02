<%-- 
    Document   : error
    Created on : Oct 2, 2021, 10:23:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:if test="${errorMess != null}">
            <p style="color: red; font-weight: bold;">
            <c:out value="${errorMess}"/>
            </p>
    </c:if>
</body>
</html>
