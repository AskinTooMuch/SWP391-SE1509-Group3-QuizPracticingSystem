<%-- 
    Document   : userProfile
    Created on : Sep 26, 2021, 2:40:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:out value="${currUser.getProfilePic()}" ></c:out>
        <img src="/web/upload/${currUser.getProfilePic()}">
    </body>
</html>
